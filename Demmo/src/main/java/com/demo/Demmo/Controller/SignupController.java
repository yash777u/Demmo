package com.demo.Demmo.Controller;

import com.demo.Demmo.entity.User;
import com.demo.Demmo.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            signupService.registerUser(user.getUsername(), user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public  ResponseEntity<String> car()
    {
        return ResponseEntity.ok("Cars");
    }


}

