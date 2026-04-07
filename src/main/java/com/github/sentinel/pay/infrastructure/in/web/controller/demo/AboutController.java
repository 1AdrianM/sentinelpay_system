package com.github.sentinel.pay.infrastructure.in.web.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
