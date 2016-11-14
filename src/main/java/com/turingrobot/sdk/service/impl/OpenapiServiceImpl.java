package com.turingrobot.sdk.service.impl;

import com.turingrobot.sdk.config.OpenapiConfig;
import com.turingrobot.sdk.service.OpenapiService;
import com.turingrobot.sdk.util.JsonUtils;
import com.turingrobot.sdk.util.WebUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
                param.put("data", encoder(param, timeMillis));
            } catch (Exception e) {
                throw new RuntimeException("encode error", e);
            }
            param.remove("info");
//            param.remove("userid");

            param.put("timestamp", timeMillis);
        } else {
            //普通
            if (StringUtils.isNotBlank(location)) {
                param.put("loc", location);
            }
        }
        return JsonUtils.toObject(WebUtils.post("http://www.tuling123.com/openapi/api", JsonUtils.toJson(param)), LinkedHashMap.class);
    }

    private String encoder(Map<String, Object> param, long timeMillis) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        String aesKey = DigestUtils.md5Hex(openapiConfig.getSecretKey() + timeMillis + openapiConfig.getApikey());
        SecretKeySpec secretKeySpec = new SecretKeySpec(DigestUtils.md5(aesKey), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0}));
        byte[] bytes = cipher.doFinal(JsonUtils.toJson(param).getBytes());
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }

    public OpenapiConfig getOpenapiConfig() {
        return openapiConfig;
    }

    public void setOpenapiConfig(OpenapiConfig openapiConfig) {
        this.openapiConfig = openapiConfig;
    }
}
