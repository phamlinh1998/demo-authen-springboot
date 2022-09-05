package com.example.mongo_user.domain.factory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestFactory {
  private final RestTemplate restTemplate;

  public RequestFactory(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public boolean test() {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> bodyRequest = new HashMap<>();

    HttpEntity requestEntity = new HttpEntity<>(bodyRequest, requestHeaders);
    ResponseEntity<Boolean> response =
            restTemplate.postForEntity(
                    "http://localhost:8081/v1/tests/abc", requestEntity, Boolean.class);

    return response.getBody();
  }
}
