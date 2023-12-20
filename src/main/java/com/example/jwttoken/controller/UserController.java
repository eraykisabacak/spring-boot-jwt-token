package com.example.jwttoken.controller;

import com.example.jwttoken.dto.AuthRequest;
import com.example.jwttoken.dto.CreateUserRequest;
import com.example.jwttoken.model.User;
import com.example.jwttoken.response.CreateUserResponse;
import com.example.jwttoken.service.JwtService;
import com.example.jwttoken.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        log.info("Welcome Page");
        return ResponseEntity.ok("Welcome Page");
    }

    @PostMapping("/register")
    public CreateUserResponse addNewUser(@RequestBody CreateUserRequest createUserRequest){
        log.info("Add New User {} " + createUserRequest.username());
        return userService.createUser(createUserRequest);
    }

    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.username());
        }
        log.info("Invalid username {} " + authRequest.username());
        throw new UsernameNotFoundException("invalid username {}" + authRequest.username());
    }

    @GetMapping("/user")
    public String getUser(){
        return "getUser endpoint";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "getAdmin endpoint";
    }

}
