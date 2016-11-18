package com.turingrobot.sdk.service;

import com.turingrobot.sdk.TuringRobotSettingAware;
import com.turingrobot.sdk.entity.KnowledgeBase;
import com.turingrobot.sdk.entity.Pageable;

import java.util.Map;

/**
 * Service - 知识库接口
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public interface KnowledgeBaseService extends TuringRobotSettingAware {
    /**
     * 设置匹配度
     *
     * @param degree 匹配度
     * @return 接口返回数据
     */
    Map setMatch(int degree);

    /**
     * 设置加密开关
     *
     * @param secretSwitch 加密开关
     * @return 接口返回数据
     */
    Map setSecretSwitch(boolean secretSwitch);

    /**
     * 设置加密KEY
     *
     * @param secretKey 加密KEY
     * @return 接口返回数据
     */
    Map setSecretKey(String secretKey);

    /**
     * 设置加密开关和加密KEY
     *
     * @param secretSwitch 加密开关
     * @param secretKey    加密KEY
     * @return 接口返回数据
     */
    Map setSecretSwitchAndSecretKey(boolean secretSwitch, String secretKey);

    /**
     * 知识库添加
     *
     * @param knowledgeBase 知识库数据，需要字段：question、answer
     * @return 接口返回数据
     */
    Map add(KnowledgeBase... knowledgeBase);

    /**
     * 知识库查找
     *
     * @param pageable 分页信息
     * @return 接口返回数据
     */
    Map findByPageable(Pageable pageable);

    /**
     * 知识库修改
     *
     * @param knowledgeBase 知识库数据
     * @return 接口返回数据
     */
    Map update(KnowledgeBase... knowledgeBase);

    /**
     * 知识库删除
     *
     * @param id 知识库数据ID
     * @return 接口返回数据
     */
    Map delete(String... id);

    /**
     * 知识库清空
     *
     * @return 接口返回数据
     */
    Map clear();

}
