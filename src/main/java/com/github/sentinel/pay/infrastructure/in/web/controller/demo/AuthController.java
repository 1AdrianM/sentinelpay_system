package com.github.sentinel.pay.infrastructure.in.web.controller.demo;

import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
 public class AuthController {
    private final AuthService authService;
     @GetMapping("/signup")
    public String signup(Model model){
         model.addAttribute("usuario", new UserDto());
         System.out.println("🔥 ENTRÓ /signup");
         return "register";}

    @PostMapping("/do-signup")
    public String signupPost(@Valid @ModelAttribute("usuario") UserDto usuario,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "register"; // vuelve al formulario mostrando errores
        }
        authService.register(usuario);
        System.out.println("🔥 ENTRÓ /signup");
        return "redirect:dashboard";}

    @GetMapping("/signin")
    public String login(Model model){
        System.out.println("🔥 ENTRÓ /signin");

        return "login";}
}
