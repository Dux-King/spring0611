package com.gpcomb.spring0611.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王东旭
 * @date 2018-06-11
 */
@RestController
@RequestMapping("/test-security")
public class TestResource {
    @GetMapping("have")
    public String has(){
        return "have";
    }
    @GetMapping("no")
    public String no(){
        return "no";
    }
}
