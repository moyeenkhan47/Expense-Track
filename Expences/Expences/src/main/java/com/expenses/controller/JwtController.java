package com.expenses.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.config.CustomUserDetailService;
import com.expenses.security.JwtHelper;
import com.expenses.security.JwtRequest;
import com.expenses.security.JwtResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
@Builder

public class JwtController {

    

    private AuthenticationManager manager;

    private JwtHelper helper;
    
    private CustomUserDetailService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

    	 this.doAuthenticate(request.getUsername(), request.getPassword());


         UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
         String token = this.helper.generateToken(userDetails);

         JwtResponse response = JwtResponse.builder()
                
                 .username(userDetails.getUsername())
                .jwtToken(token).build();
         return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }

    }
}