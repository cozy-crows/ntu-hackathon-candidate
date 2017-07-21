package com.example.demo.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jerry on 2017/7/21.
 */
@RestController
@RequestMapping("/api/facebook/login")
public class FacebookLoginResource {

    @RequestMapping(
            value = "hook",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public void loginHook() {

    }
}
