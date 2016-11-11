package com.turingrobot.sdk.service.impl;

import com.turingrobot.sdk.config.OpenapiConfig;
import com.turingrobot.sdk.service.OpenapiService;
import com.turingrobot.sdk.util.JsonUtils;
import com.turingrobot.sdk.util.WebUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Service - openapi语义解析接口默认实现
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class OpenapiServiceImpl implements OpenapiService {

    private OpenapiConfig openapiConfig;

    public OpenapiServiceImpl(OpenapiConfig openapiConfig) {
        this.openapiConfig = openapiConfig;
    }

    @Override
    public Map<String, Object> webapi(String cmd, String userid) {
        return webapi(cmd, userid, null);
    }

    @Override
    public Map<String, Object> webapi(String cmd, String userid, String location) {
        //本地参数校验
        if (cmd == null || StringUtils.isBlank(cmd)) {
            LinkedHashMap<String, Object> result = new LinkedHashMap<>();
            result.put("code", 40002);
            result.put("text", "请求内容info为空");
            return result;
        }
        //拼装请求参数
        LinkedHashMap<String, Object> param = new LinkedHashMap<>();
        param.put("key", openapiConfig.getApikey());
        param.put("info", cmd);
        param.put("userid", userid);
        if (openapiConfig.getSecretSwitch()) {
            //加密
            long timeMillis = System.currentTimeMillis();
            try {
                String aesKey = DigestUtils.md5Hex(openapiConfig.getSecretKey() + timeMillis + openapiConfig.getApikey());
                SecretKeySpec secretKeySpec = new SecretKeySpec(DigestUtils.md5(aesKey), "AES");
                System.out.println(new String(Base64.encodeBase64(secretKeySpec.getEncoded())));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0}));
                byte[] encryptBytes = cipher.doFinal(JsonUtils.toJson(param).getBytes());
                param.put("data", new String(Base64.encodeBase64(encryptBytes), "UTF-8"));
            } catch (Exception e) {
                throw new RuntimeException("encode error", e);
            }
            param.remove("info");
            param.remove("userid");
            param.put("timestamp", timeMillis);
        } else {
            //普通
            if (StringUtils.isNotBlank(location)) {
                param.put("loc", location);
            }
        }
        return JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/openapi/api", JsonUtils.toJson(param)), LinkedHashMap.class);
    }

    public OpenapiConfig getOpenapiConfig() {
        return openapiConfig;
    }

    public void setOpenapiConfig(OpenapiConfig openapiConfig) {
        this.openapiConfig = openapiConfig;
    }

    public static void main(String[] args) throws Exception {
        OpenapiService openapiService = new OpenapiServiceImpl(new OpenapiConfig("2d3c293c7a9641948017b2333d3321c9","22c5daa34395b3cb"));
        Map<String, Object> webapi = openapiService.webapi("你好啊", "1111");
        System.out.println(JsonUtils.toJson(webapi));
    }
}
