package org.automation.restassuredframework.services;

import io.restassured.response.Response;
import org.automation.restassuredframework.base.BaseService;
import org.automation.restassuredframework.models.request.CreateProductRequest;

public class ProductService extends BaseService {

  public Response createProduct(CreateProductRequest request, String token) {
    if (token != null) {
      setAuthToken(token);
    }
    return post("/products", request);
  }
}