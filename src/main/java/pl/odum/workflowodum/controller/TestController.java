package pl.odum.workflowodum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class TestController {

    @ResponseBody
    @RequestMapping("/")
    public String hello(){
        return "hello";
    }
}
