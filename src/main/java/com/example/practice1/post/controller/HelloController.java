package com.example.practice1.post.controller;

import com.example.practice1.post.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final HelloService helloService;

  @Autowired
  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }


  @GetMapping("/hello")
  public String hello() {
    String str = helloService.getString();
    return str;
  }
}

// 주석
