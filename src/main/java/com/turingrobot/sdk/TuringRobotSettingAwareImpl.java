package com.turingrobot.sdk;

import com.turingrobot.sdk.config.TuringRobotSetting;
import org.apache.commons.lang.Validate;

/**
 * Aware - 机器人设置写入
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public class TuringRobotSettingAwareImpl implements TuringRobotSettingAware {

    protected TuringRobotSetting turingRobotSetting;

    public TuringRobotSettingAwareImpl(TuringRobotSetting turingRobotSetting) {
        setTuringRobotSetting(turingRobotSetting);
    }

    public TuringRobotSetting getTuringRobotSetting() {
        return turingRobotSetting;
    }

    public void setTuringRobotSetting(TuringRobotSetting turingRobotSetting) {
        Validate.notNull(turingRobotSetting, "parameter turingRobotSetting Cannot set null");
        this.turingRobotSetting = turingRobotSetting;
    }
}
