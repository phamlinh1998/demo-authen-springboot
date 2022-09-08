package com.example.mongo_user.app.controllers;

import com.example.mongo_user.domain.factory.RequestFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/tests")
@Log4j2
public class TestController {
  @Autowired private RequestFactory requestFactory;
  @PostMapping("abc")
  public boolean test1(@RequestHeader String username, @RequestHeader String password){
    log.info("================ username: " + username);
    log.info("================ password: " + password);
    return true;
  }

  @GetMapping
  public boolean test2(){
    return requestFactory.test();
  }
}
