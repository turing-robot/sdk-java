package com.turingrobot.sdk.config;

import org.apache.commons.lang.Validate;

/**
 * Config - 机器人设置
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class TuringRobotSetting {

    /**
     * apikey
     */
    private String apikey;

    /**
     * 加密开关
     */
    private boolean secretSwitch = false;

    /**
     * 加密密匙
     */
    private String secretKey;

    /**
     * 知识库设置
     */
    private KnowledgeBaseSetting knowledgeBaseSetting = new KnowledgeBaseSetting();

    /**
     * 不加密的设置
     *
     * @param apikey 图灵机器人APIKEY
     */
    public TuringRobotSetting(String apikey) {
        setApikey(apikey);
    }

    /**
     * 加密设置
     *
     * @param apikey    图灵机器人APIKEY
     * @param secretKey 加密密匙
     */
    public TuringRobotSetting(String apikey, String secretKey) {
        setApikey(apikey);
        setSecretSwitch(true);
        setSecretKey(secretKey);
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        Validate.notEmpty(apikey, "parameter apikey Cannot set empty");
        apikey = apikey.trim();
        Validate.isTrue(apikey.length() == 32, "apikey length is ", 32);
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
        Validate.notEmpty(secretKey, "parameter secretKey Cannot set empty");
        secretKey = secretKey.trim();
        Validate.isTrue(secretKey.length() == 16, "parameter secretKey length is ", 16);
        this.secretKey = secretKey;
    }

    public KnowledgeBaseSetting getKnowledgeBaseSetting() {
        return knowledgeBaseSetting;
    }

    public void setKnowledgeBaseSetting(KnowledgeBaseSetting knowledgeBaseSetting) {
        Validate.notEmpty(secretKey, "parameter knowledgeBaseSetting Cannot set null");
        this.knowledgeBaseSetting = knowledgeBaseSetting;
    }

    @Override
    public String toString() {
        return "TuringRobotSetting{" +
                "apikey='" + apikey + '\'' +
                ", secretSwitch=" + secretSwitch +
                ", secretKey='" + secretKey + '\'' +
                ", knowledgeBaseSetting=" + knowledgeBaseSetting +
                '}';
    }
}
