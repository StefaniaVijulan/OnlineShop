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
            throw new IllegalStateException("Acest email nu exista!");
        }

        Designer designer = designerRepository.findByEmail(emailDesigner);
        String pass = designer.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Cnp doesnt exist");

        }
        System.out.println(designer);
        return designer;
    }

    public Designer addDesigner(Designer designer) {
        if (designerRepository.existsByEmail(designer.getEmail())) {
            //designerul exista
            return null;

        }
        String parola = "parola";
        designer.setPasswordDesigner(bCryptPasswordEncoder.encode(parola));

        designer.setRole("DESIGNER");
        designerRepository.save(designer);
        return designer;
    }

    public List<Designer> getDesigners() {
        System.out.println("Cine");
        List<Designer> designers = new ArrayList<>();
        for (int i = 0; i < designerRepository.findAll().size(); i++) {
            {
                designers.add(designerRepository.findAll().get(i));
            }
        }
        System.out.println("here");
        System.out.println(designers);
        return designers;
    }
}
