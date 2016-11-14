package test.com.turingrobot.sdk.service.impl;

import com.turingrobot.sdk.config.OpenapiConfig;
import com.turingrobot.sdk.service.OpenapiService;
import com.turingrobot.sdk.service.impl.OpenapiServiceImpl;
import org.junit.Test;

import java.util.Map;

/**
 * Test - openapi语义解析接口
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class TestOpenapiServieImpl {
    @Test
    public void testWebapi() {
        String apikey = "f0b3264bc2414769bbc96b0eae207e9e";//机器人apikey
        //不加密
        OpenapiConfig openapiConfig = new OpenapiConfig(apikey);

        OpenapiService openapiService = new OpenapiServiceImpl(openapiConfig);
        //请求api
        String userid = "123456";
        String info = "你好";
        Map<String, Object> result = openapiService.webapi(info, userid);
        //打印结果
        if (!result.get("code").equals(40001)) {
            throw new RuntimeException("请求结果错误");
        }
    }

    @Test
    public void testWebapiSecret() {
        String apikey = "f0b3264bc2414769bbc96b0eae207e9e";//机器人apikey
        //加密
        String secret = "22c5daa34395b3cb";//机器人加密密匙
        OpenapiConfig openapiConfig = new OpenapiConfig(apikey, secret);

        OpenapiService openapiService = new OpenapiServiceImpl(openapiConfig);
        //请求api
        String userid = "123456";
        String info = "你好";
        Map<String, Object> result = openapiService.webapi(info, userid);
        //打印结果
        if (!result.get("code").equals(40001)) {
            throw new RuntimeException("请求结果错误");
        }
    }
}
