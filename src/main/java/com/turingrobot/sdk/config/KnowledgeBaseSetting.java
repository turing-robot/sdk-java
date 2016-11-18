package com.turingrobot.sdk.config;

/**
 * Config - 知识库设置
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class KnowledgeBaseSetting {

    /**
     * 加密开关
     */
    private boolean secretSwitch = false;

    /**
     * 加密密匙
     */
    private String secretKey;

    public KnowledgeBaseSetting() {
    }

    public KnowledgeBaseSetting(String secretKey) {
        this.secretSwitch = true;
        this.secretKey = secretKey;
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
        return "KnowledgeBaseSetting{" +
                "secretSwitch=" + secretSwitch +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
