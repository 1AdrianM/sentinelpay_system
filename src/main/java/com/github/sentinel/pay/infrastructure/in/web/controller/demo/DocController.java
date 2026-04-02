package com.github.sentinel.pay.infrastructure.in.web.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocController {
    @GetMapping("/swagger-docs")
    public String swaggerDocs() {
        return "redirect:/swagger-ui/index.html";
    }


    @GetMapping("/api-docs")
    public String docsPage(){

        return "api-docs";

     }
}
