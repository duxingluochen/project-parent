package com.mapscience.core.util;

import com.mapscience.config.properties.ProjectProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {
    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(ProjectProperties.class).getKaptchaOpen();
    }
}
