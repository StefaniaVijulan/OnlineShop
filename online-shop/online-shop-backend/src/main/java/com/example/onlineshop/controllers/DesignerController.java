package com.example.onlineshop.controllers;

import com.example.onlineshop.security.config.JwtUtil;
import com.example.onlineshop.security.dto.LoginDesignerResponse;
import com.example.onlineshop.security.dto.LoginRequest;
import com.example.onlineshop.security.models.Designer;
import com.example.onlineshop.security.services.DesignerService;
import com.example.onlineshop.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/designer")
@RequiredArgsConstructor
public class DesignerController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private DesignerService designerService;

    @PostMapping(path = "/loginDesigner")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        System.out.println("Ajunge aici");
        System.out.println(loginRequest);
        //    excelReadService.ReadDataFromExcel("src/main/resources/excelFile/UserDB.xlsx");

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            loginRequest.getPassword()));
        }
        catch (Exception e) {
            System.out.println("Exceptie");
            System.out.println(e);
            return null;
        }
        final UserDetails userDetails = userService
                .loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        Designer currentUser= designerService.loginDesigner(loginRequest.getUsername(), loginRequest.getPassword());
        System.out.println("currentUser "+ currentUser);
        return ResponseEntity.ok(new LoginDesignerResponse(jwt, currentUser));
    }

    @PostMapping(path = "/registerDesigner")
    public Designer register(@RequestBody Designer designer) throws Exception {
        return designerService.addDesigner(designer);
    }
    @GetMapping(path="/all")
    public ResponseEntity<List<Designer>> getProducts()
    {
        return ResponseEntity.ok(designerService.getDesigners());
    }
}
