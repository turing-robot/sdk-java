package com.turingrobot.sdk.service;

import com.turingrobot.sdk.TuringRobotSettingAware;

import java.util.Map;

/**
 * Service - 聊天接口
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public interface OpenapiService extends TuringRobotSettingAware {

    /**
     * 聊天
     *
     * @param cmd    聊天内容
     * @param userid 用户标识
     * @return 接口返回数据
     */
    Map webapi(String cmd, String userid);

    /**
     * 聊天
     *
     * @param cmd      聊天内容
     * @param userid   用户标识
     * @param location 地理位置
     * @return 接口返回数据
     */
    Map webapi(String cmd, String userid, String location);

}
