package org.automation.restassuredframework.services;

import io.restassured.response.Response;
import org.automation.restassuredframework.base.BaseService;
import org.automation.restassuredframework.models.request.LoginRequest;

public class AuthService extends BaseService {

    public Response login(LoginRequest request) {
      return post("/auth/login", request);
    }

}
