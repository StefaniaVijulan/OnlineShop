package com.example.onlineshop.controllers;

import com.example.onlineshop.security.config.JwtUtil;
import com.example.onlineshop.security.dto.LoginRequest;
import com.example.onlineshop.security.dto.LoginResponse;
import com.example.onlineshop.security.models.User;
import com.example.onlineshop.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        //    excelReadService.ReadDataFromExcel("src/main/resources/excelFile/UserDB.xlsx");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            return null;
        }
        final UserDetails userDetails = userService
                .loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        User currentUser= userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println(new LoginResponse(jwt, currentUser));
        return ResponseEntity.ok(new LoginResponse(jwt, currentUser));
    }
    @PostMapping(path = "/register")
    public User registerModerator(@RequestBody User user) throws Exception {
        return userService.registerUser(user);
    }

    @GetMapping(path="/changePass")
    public User changePass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, @RequestParam("cnpU")String cnpC) throws Exception {
        return userService.changePassword(oldPass, newPass, cnpC);
    }

    @PutMapping(("/forgotPass"))
    public User forgotPass(@RequestParam("cnpU")String cnpU)  {
        return userService.forgotPass(cnpU);
    }
}
