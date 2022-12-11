package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.Designer;
import com.example.onlineshop.security.models.User;
import com.example.onlineshop.security.repositories.DesignerRepository;
import com.example.onlineshop.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerService{


    @Autowired
    private DesignerRepository designerRepository;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Designer loginDesigner(String emailDesigner, String password){
        if (!designerRepository.existsByEmail(emailDesigner)) {
            throw new IllegalStateException("User don't exist!");
        }

        Designer designer = designerRepository.findByEmail(emailDesigner);
        String pass = designer.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Passwords doesn't match");

        }
        System.out.println(designer);
        return designer;
    }

    public Designer addDesigner(Designer designer) {
        if (designerRepository.existsByEmail(designer.getEmail())) {
            return null;
        }
        designer.setPasswordDesigner(bCryptPasswordEncoder.encode(designer.getPasswordDesigner()));
        designer.setRole("DESIGNER");
        designerRepository.save(designer);
        return designer;
    }

    public List<Designer> getDesigners() {
        return designerRepository.findAll();
    }
}
