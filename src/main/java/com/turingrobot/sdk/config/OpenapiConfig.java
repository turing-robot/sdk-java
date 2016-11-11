package com.turingrobot.sdk.config;

/**
 * Config - Openapi配置
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class OpenapiConfig {

    /**
     * apikey
     */
    private String apikey;

    /**
     * 加密开关
     */
    private boolean secretSwitch;

    /**
     * 加密密匙
     */
    private String secretKey;

    /**
     * 不加密的配置
     *
     * @param apikey 图灵机器人APIKEY
     */
    public OpenapiConfig(String apikey) {
        this.apikey = apikey;
        this.secretSwitch = false;
    }

    /**
     * 加密配置
     *
     * @param apikey    图灵机器人APIKEY
     * @param secretKey 加密密匙
     */
    public OpenapiConfig(String apikey, String secretKey) {
        this.apikey = apikey;
        this.secretSwitch = true;
        this.secretKey = secretKey;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public boolean getSecretSwitch() {
        return secretSwitch;
    }

    public void setSecretSwitch(boolean secretSwitch) {
        this.secretSwitch = secretSwitch;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "OpenapiConfig{" +
                "apikey='" + apikey + '\'' +
                ", secretSwitch=" + secretSwitch +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
