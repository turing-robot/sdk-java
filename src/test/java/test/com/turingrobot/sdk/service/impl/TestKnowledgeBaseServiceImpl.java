package test.com.turingrobot.sdk.service.impl;

import com.turingrobot.sdk.config.KnowledgeBaseSetting;
import com.turingrobot.sdk.config.TuringRobotSetting;
import com.turingrobot.sdk.entity.KnowledgeBase;
import com.turingrobot.sdk.entity.Pageable;
import com.turingrobot.sdk.service.KnowledgeBaseService;
import com.turingrobot.sdk.service.impl.KnowledgeBaseServiceImpl;
import com.turingrobot.sdk.util.JsonUtils;
import org.junit.Test;

import java.util.Map;

/**
 * Test - 知识库接口测试
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class TestKnowledgeBaseServiceImpl extends BaseTest {

    public static void main(String[] args) {
        //-----------------初始化--------------------
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting("74c15b68baae4f1282395f04c2f4c764");
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();//获取知识库配置对象
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);//设置知识库加密KEY
        knowledgeBaseSetting.setSecretSwitch(true);//设置知识库加密开关
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);//生成接口调用类

        //----------------调用-------------------
        Map result;
        result = knowledgeBaseService.setMatch(50);//设置知识库匹配度
        result = knowledgeBaseService.setSecretSwitch(true);//设置知识库加密开关
        result = knowledgeBaseService.setSecretKey("D1AS5D61");//设置知识库加密KEY
        result = knowledgeBaseService.setSecretSwitchAndSecretKey(true, "D1AS5D61");//设置知识库加密开关和加密KEY
        result = knowledgeBaseService.add(new KnowledgeBase("a", "b"));//知识库添加
        result = knowledgeBaseService.findByPageable(new Pageable());//知识库查找
        result = knowledgeBaseService.findByPageable(new Pageable());//知识库查找
        result = knowledgeBaseService.update(new KnowledgeBase("a", "b", "1"));//知识库修改
        result = knowledgeBaseService.delete("123");//知识库删除
        result = knowledgeBaseService.clear();//知识库清空
    }

    @Test
    public void testSetMatch() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.setMatch(50);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testSetSecretSwitch() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.setSecretSwitch(true);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testSetSecretKey() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.setSecretKey(knowledgeBaseSetting.getSecretKey());
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testSetSecretSwitchAndSecretKey() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.setSecretSwitchAndSecretKey(true, knowledgeBaseSetting.getSecretKey());
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testAdd() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.add(new KnowledgeBase[]{new KnowledgeBase("a", "b"), new KnowledgeBase("c", "d")});
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testFindByPageable() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Pageable pageable = new Pageable();
        pageable.setSearchValue("");
        Map result = knowledgeBaseService.findByPageable(pageable);
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testUpdate() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.update(new KnowledgeBase(null, "aaaaa", "2950028"));
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testDelete() {
        String apikey = "0f240fe019224219b55581a8cb7736e6";
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(apikey);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.delete("2950027");
        System.out.println(JsonUtils.toJson(result));
    }

    @Test
    public void testClear() {
        TuringRobotSetting turingRobotSetting = new TuringRobotSetting(APIKEY);
        KnowledgeBaseSetting knowledgeBaseSetting = turingRobotSetting.getKnowledgeBaseSetting();
        knowledgeBaseSetting.setSecretKey(SECRET_KEY);
        knowledgeBaseSetting.setSecretSwitch(true);
        KnowledgeBaseService knowledgeBaseService = new KnowledgeBaseServiceImpl(turingRobotSetting);
        Map result = knowledgeBaseService.clear();
        System.out.println(JsonUtils.toJson(result));
    }
}
