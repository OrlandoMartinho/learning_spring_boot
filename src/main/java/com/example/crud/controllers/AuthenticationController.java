package com.example.crud.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.crud.domain.user.AuthenticationDTO;
import com.example.crud.domain.user.RegisterDTO;
import com.example.crud.domain.user.User;
import com.example.crud.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new  UsernamePasswordAuthenticationToken(
                data.login(),
                data.password()
        );

        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
       
        if (userRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        
        var user = new User(
                data.login(),
                encryptedPassword, 
                data.role()
        );

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }   
    
}
