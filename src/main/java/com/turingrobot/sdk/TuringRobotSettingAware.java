package com.turingrobot.sdk;

import com.turingrobot.sdk.config.TuringRobotSetting;

/**
 * Aware - 机器人设置写入
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public interface TuringRobotSettingAware {

    /**
     * 获取机器人设置
     *
     * @return 机器人设置
     */
    TuringRobotSetting getTuringRobotSetting();

    /**
     * 写入机器人设置
     *
     * @param turingRobotSetting 机器人设置
     */
    void setTuringRobotSetting(TuringRobotSetting turingRobotSetting);

}
