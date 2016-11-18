package com.turingrobot.sdk.service.impl;

import com.turingrobot.sdk.TuringRobotSettingAwareImpl;
import com.turingrobot.sdk.config.TuringRobotSetting;
import com.turingrobot.sdk.entity.KnowledgeBase;
import com.turingrobot.sdk.entity.Pageable;
import com.turingrobot.sdk.service.KnowledgeBaseService;
import com.turingrobot.sdk.util.JsonUtils;
import com.turingrobot.sdk.util.WebUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.util.*;

/**
 * Service - 知识库接口
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class KnowledgeBaseServiceImpl extends TuringRobotSettingAwareImpl implements KnowledgeBaseService {

    public KnowledgeBaseServiceImpl(TuringRobotSetting turingRobotSetting) {
        super(turingRobotSetting);
    }

    @Override
    public Map setMatch(int degree) {
        if (degree < 10 || degree > 100) {
            Map result = new LinkedHashMap();
            result.put("code", 501);
            result.put("data", "匹配度不正确");
            return result;
        }

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("apikey", turingRobotSetting.getApikey());
        HashMap<Object, Object> data = new HashMap<>();
        data.put("match", degree);
        param.put("data", data);
        if (turingRobotSetting.getKnowledgeBaseSetting().getSecretSwitch()) {
            long timeMillis = System.currentTimeMillis();
            param.put("timestamp", timeMillis);
            param.put("token", DigestUtils.md5Hex(turingRobotSetting.getKnowledgeBaseSetting().getSecretKey() + timeMillis));
        }
        Map result = JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/v1/kb/match", JsonUtils.toJson(param)), LinkedHashMap.class);
        return result;
    }

    @Override
    public Map setSecretSwitch(boolean secretSwitch) {
        return setSecretSwitchAndSecretKey(Boolean.valueOf(secretSwitch), null);
    }

    @Override
    public Map setSecretKey(String secretKey) {
        Validate.notEmpty(secretKey, "parameter secretKey Cannot empty");
        return setSecretSwitchAndSecretKey(null, secretKey);
    }

    @Override
    public Map setSecretSwitchAndSecretKey(boolean secretSwitch, String secretKey) {
        Validate.notEmpty(secretKey, "parameter secretKey Cannot empty");
        return setSecretSwitchAndSecretKey(Boolean.valueOf(secretSwitch), secretKey);
    }

    protected Map setSecretSwitchAndSecretKey(Boolean secretSwitch, String secretKey) {
        Map<String, Object> param = new LinkedHashMap<>();
        param.put("apikey", turingRobotSetting.getApikey());
        if (turingRobotSetting.getKnowledgeBaseSetting().getSecretSwitch()) {
            long timeMillis = System.currentTimeMillis();
            param.put("timestamp", timeMillis);
            param.put("token", DigestUtils.md5Hex(turingRobotSetting.getKnowledgeBaseSetting().getSecretKey() + timeMillis));
        }
        HashMap<Object, Object> data = new HashMap<>();
        param.put("data", data);
        if (secretSwitch != null) {
            data.put("mod", secretSwitch);
        }
        if (secretKey != null) {
            data.put("secret", secretKey);
        }
        Map result = JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/v1/kb/secret", JsonUtils.toJson(param)), LinkedHashMap.class);
        return result;
    }

    @Override
    public Map add(KnowledgeBase... knowledgeBase) {
        Validate.notEmpty(knowledgeBase, "parameter knowledgeBase Cannot empty");
        List<KnowledgeBase> knowledgeBases = Arrays.asList(knowledgeBase);

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("apikey", turingRobotSetting.getApikey());
        if (turingRobotSetting.getKnowledgeBaseSetting().getSecretSwitch()) {
            long timeMillis = System.currentTimeMillis();
            param.put("timestamp", timeMillis);
            param.put("token", DigestUtils.md5Hex(turingRobotSetting.getKnowledgeBaseSetting().getSecretKey() + timeMillis));
        }
        HashMap<Object, Object> data = new HashMap<>();
        param.put("data", data);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        data.put("list", list);
        for (int i = 0; i < knowledgeBases.size(); i++) {
            KnowledgeBase base = knowledgeBases.get(i);
            HashMap<String, String> tempMap = new HashMap<>();
            String question = base.getQuestion();
            Validate.notEmpty(question, "knowledgeBase question Cannot empty in index " + i);
            Validate.isTrue(question.length() <= 200, "knowledgeBase question length max ", 200);

            tempMap.put("question", question);
            String answer = base.getAnswer();
            if (StringUtils.isNotBlank(answer)) {
                tempMap.put("answer", answer);
            }
            list.add(tempMap);
        }
        Map result = JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/v1/kb/import", JsonUtils.toJson(param)), LinkedHashMap.class);
        parseKnowledgeBase(result);
        return result;
    }

    @Override
    public Map findByPageable(Pageable pageable) {
        Validate.notNull(pageable, "parameter pageable Cannot null");

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("apikey", turingRobotSetting.getApikey());
        if (turingRobotSetting.getKnowledgeBaseSetting().getSecretSwitch()) {
            long timeMillis = System.currentTimeMillis();
            param.put("timestamp", timeMillis);
            param.put("token", DigestUtils.md5Hex(turingRobotSetting.getKnowledgeBaseSetting().getSecretKey() + timeMillis));
        }
        HashMap<Object, Object> data = new HashMap<>();
        param.put("data", data);

        HashMap<Object, Object> pages = new HashMap<>();
        data.put("pages", pages);
        pages.put("pageNumber", pageable.getPageNumber());
        pages.put("pageSize", pageable.getPageSize());
        if (StringUtils.isBlank(pageable.getSearchValue())) {
            pages.put("searchBy", pageable.getSearchValue());
        }
        Map result = JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/v1/kb/select", JsonUtils.toJson(param)), LinkedHashMap.class);
        parseKnowledgeBase(result);
        return result;
    }

    private void parseKnowledgeBase(Map result) {
        Object data = result.get("data");
        if (data != null && data instanceof Map) {
            Map dataMap = (Map) data;
            Object knowledgeList = dataMap.get("knowledgeList");
            List<KnowledgeBase> parseList = new ArrayList<>();
            if (knowledgeList != null && knowledgeList instanceof List) {
                try {
                    for (Object knowledge : (List) knowledgeList) {
                        String json = JsonUtils.toJson(knowledge);
                        parseList.add(JsonUtils.toObject(json, KnowledgeBase.class));
                    }
                    dataMap.remove("knowledgeList");
                    dataMap.put("knowledgeList", parseList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Map update(KnowledgeBase... knowledgeBase) {
        Validate.notEmpty(knowledgeBase, "parameter knowledgeBase Cannot empty");
        List<KnowledgeBase> knowledgeBases = Arrays.asList(knowledgeBase);

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("apikey", turingRobotSetting.getApikey());
        if (turingRobotSetting.getKnowledgeBaseSetting().getSecretSwitch()) {
            long timeMillis = System.currentTimeMillis();
            param.put("timestamp", timeMillis);
            param.put("token", DigestUtils.md5Hex(turingRobotSetting.getKnowledgeBaseSetting().getSecretKey() + timeMillis));
        }
        HashMap<Object, Object> data = new HashMap<>();
        param.put("data", data);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        data.put("list", list);
        for (int i = 0; i < knowledgeBases.size(); i++) {
            KnowledgeBase base = knowledgeBases.get(i);
            HashMap<String, String> tempMap = new HashMap<>();
            String id = base.getId();
            Validate.notEmpty(id, "knowledgeBase id Cannot empty in index " + i);
            tempMap.put("id", id);
            String question = base.getQuestion();
            if (StringUtils.isNotBlank(question)) {
                Validate.isTrue(question.length() <= 200, "knowledgeBase question length max ", 200);
                tempMap.put("question", question);
            }
            String answer = base.getAnswer();
            if (StringUtils.isNotBlank(answer)) {
                tempMap.put("answer", answer);
            }

            list.add(tempMap);
        }

        Map result = JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/v1/kb/update", JsonUtils.toJson(param)), LinkedHashMap.class);
        return result;
    }

    @Override
    public Map delete(String... id) {
        Validate.notEmpty(id, "parameter knowledgeBase Cannot empty");
        List<String> ids = Arrays.asList(id);

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("apikey", turingRobotSetting.getApikey());
        if (turingRobotSetting.getKnowledgeBaseSetting().getSecretSwitch()) {
            long timeMillis = System.currentTimeMillis();
            param.put("timestamp", timeMillis);
            param.put("token", DigestUtils.md5Hex(turingRobotSetting.getKnowledgeBaseSetting().getSecretKey() + timeMillis));
        }
        HashMap<Object, Object> data = new HashMap<>();
        param.put("data", data);
        data.put("ids", ids);
        data.put("clear", false);

        Map result = JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/v1/kb/delete", JsonUtils.toJson(param)), LinkedHashMap.class);
        return result;
    }

    @Override
    public Map clear() {

        Map<String, Object> param = new LinkedHashMap<>();
        param.put("apikey", turingRobotSetting.getApikey());
        if (turingRobotSetting.getKnowledgeBaseSetting().getSecretSwitch()) {
            long timeMillis = System.currentTimeMillis();
            param.put("timestamp", timeMillis);
            param.put("token", DigestUtils.md5Hex(turingRobotSetting.getKnowledgeBaseSetting().getSecretKey() + timeMillis));
        }
        HashMap<Object, Object> data = new HashMap<>();
        param.put("data", data);
        data.put("clear", true);

        Map result = JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/v1/kb/delete", JsonUtils.toJson(param)), LinkedHashMap.class);
        return result;
    }
}
