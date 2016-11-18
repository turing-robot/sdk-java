package test.com.turingrobot.sdk.service.impl;

import com.turingrobot.sdk.config.TuringRobotSetting;
import com.turingrobot.sdk.service.OpenapiService;
import com.turingrobot.sdk.service.impl.OpenapiServiceImpl;
import com.turingrobot.sdk.util.JsonUtils;
import org.junit.Test;

import java.util.Map;

/**
 * Test - 聊天接口测试
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class TestOpenapiServieImpl extends BaseTest {
    @Test
    public void testWebapi() {
        String apikey = turingRobotSetting.getApikey();//机器人apikey
        //不加密
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(apikey);

        OpenapiService openapiService = new OpenapiServiceImpl(turingRobotSetting);
        //请求api
        String userid = "123456";
        String info = "你好";
        Map<String, Object> result = openapiService.webapi(info, userid);
        //打印结果
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testWebapiSecret() {
        String apikey = turingRobotSetting.getApikey();//机器人apikey
        //加密
        String secret = "22c5daa34395b3cb";//机器人加密密匙
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(apikey, secret);

        OpenapiService openapiService = new OpenapiServiceImpl(turingRobotSetting);
        //请求api
        String userid = "123456";
        String info = "你好";
        Map<String, Object> result = openapiService.webapi(info, userid);
        //打印结果
        System.out.println(JsonUtils.toJson(result));
    }
}
