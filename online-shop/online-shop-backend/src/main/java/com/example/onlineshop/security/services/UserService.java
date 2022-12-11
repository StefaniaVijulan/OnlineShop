package com.example.onlineshop.security.services;

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

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private DesignerRepository designerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException(
                    String.format("username with %s email not found", email));
        }
        return user;
    }
    public User loginUser(String email, String password){
        if (!userRepository.existsByEmail(email)) {
            throw new IllegalStateException("User don't exist");
        }
        User userProfile = userRepository.findByEmail(email);
        String pass = userProfile.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Passwords doesn't match");

        }
        return userProfile;
    }

    public User registerUser(User user) throws Exception {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (userOptional.isPresent()) {
            throw new IOException("Exista userul deja");
        }
        else
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRole("CLIENT");
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    public User changePassword(String oldPass, String newPass, String email) {
        User currentUser = this.userRepository.findByEmail(email);
        if(bCryptPasswordEncoder.matches(oldPass,(currentUser.getPassword()))){
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));
            this.userRepository.save(currentUser);
            return currentUser;
        }
        else
        {
            return null;
        }
    }
    public User forgotPass(String email) {
        User currentUser = userRepository.findByEmail(email);
        if(currentUser == null)
            return null;
        String newParola = "parola2";
        currentUser.setPassword(this.bCryptPasswordEncoder.encode(newParola));
        this.userRepository.save(currentUser);
        return currentUser;
    }

}
