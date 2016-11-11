package com.turingrobot.sdk.service;

import java.util.Map;

/**
 * Service - openapi语义解析接口
 *
 * @author TURINGROBOT Team
 * @version 1.0
 */
public interface OpenapiService {

    Map<String, Object> webapi(String cmd, String userid);

    Map<String, Object> webapi(String cmd, String userid, String location);
}
