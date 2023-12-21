package com.example.jwttoken.controller;

import com.example.jwttoken.dto.AuthRequest;
import com.example.jwttoken.dto.CreateUserRequest;
import com.example.jwttoken.response.CreateUserResponse;
import com.example.jwttoken.response.LoginResponse;
import com.example.jwttoken.service.AuthenticationService;
import com.example.jwttoken.service.JwtService;
import com.example.jwttoken.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;


    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        log.info("Welcome Page");
        return ResponseEntity.ok("Welcome Page");
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> addNewUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        log.info("Add New User {} " + createUserRequest.getUsername());
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> generateToken(@Valid @RequestBody AuthRequest authRequest) {
        log.info("Login, username {} " + authRequest.getUsername());
        return ResponseEntity.ok(authenticationService.login(authRequest));
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("getUser endpoint");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("getAdmin endpoint");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
