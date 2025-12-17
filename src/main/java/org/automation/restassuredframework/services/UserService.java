package org.automation.restassuredframework.services;

import io.restassured.response.Response;
import org.automation.restassuredframework.base.BaseService;
import org.automation.restassuredframework.models.request.CreateUserRequest;

public class UserService extends BaseService {

  public Response getUsers(int page) {
    return get("/users?page=" + page);
  }

  public Response createUser(CreateUserRequest request) {
    return post("/users", request);
  }

}

