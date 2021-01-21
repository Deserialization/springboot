package com.zs.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 18070967
 * @create 2021/1/9
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("msg","你好");
        return "test";
    }


}
