package org.automation.restassuredframework.services;

import io.restassured.response.Response;
import org.automation.restassuredframework.base.BaseService;

public class UserService extends BaseService {

  public Response getUsers(int page) {
    return get("/users?page=" + page);
  }
}

