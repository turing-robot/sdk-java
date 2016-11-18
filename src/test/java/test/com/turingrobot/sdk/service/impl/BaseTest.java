package test.com.turingrobot.sdk.service.impl;

import com.turingrobot.sdk.config.KnowledgeBaseSetting;
import com.turingrobot.sdk.config.TuringRobotSetting;

/**
 * Test - 测试基类
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public abstract class BaseTest {
    public static final String APIKEY = "74c15b68baae4f1282395f04c2f4c764";
    public static final String SECRET_KEY = "d1d56v1d1";
    protected TuringRobotSetting turingRobotSetting;

    public BaseTest() {
        this.turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
    }
}
