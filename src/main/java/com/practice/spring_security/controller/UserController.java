package com.practice.spring_security.controller;

import com.practice.spring_security.model.Userss;
import com.practice.spring_security.service.JwtService;
import com.practice.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController{

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public Userss register(@RequestBody Userss user){
        System.out.println("register request hit");
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Userss user){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(auth != null) {
            System.out.println("login request hit");
            if(auth.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }
        }
        return "Fail";
    }

    @GetMapping("/greet")
    public String greet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Userss user = userService.findByUsername(username);
        return "Hello " + user.getName() + " Welcome to Spring Security";
    }
}
