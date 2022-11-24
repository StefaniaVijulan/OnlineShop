package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.User;
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


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails user = userRepository.findByEmailUser(email);

        if(user == null){
            throw new UsernameNotFoundException(
                    String.format("username with %s email not found", email));
        }

        return user;
    }
    public User loginUser(String email, String password){
        if (!userRepository.existsByEmailUser(email)) {
            throw new IllegalStateException("Cnp doesnt exist");
        }

        User userProfile = userRepository.findByEmailUser(email);
        String pass = userProfile.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Cnp doesnt exist");

        }
        System.out.println(userProfile);
        return userProfile;
    }

    public User registerUser(User user) throws Exception {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmailUser(user.getEmailUser()));
        if (userOptional.isPresent()) {
            throw new IOException("Exista userul deja");
        }
        else
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.saveAndFlush(user);
        return user;
    };
    public User changePassword(String oldPass, String newPass, String cnpC) throws Exception {
        String username;
        username = userRepository.findByEmailUser(cnpC).getEmailUser();
        User currentUser = this.userRepository.findByEmailUser(username);
        if(this.bCryptPasswordEncoder.matches(oldPass, currentUser.getPassword()))
        {
            if(oldPass.equals(newPass))
            {System.out.println("Noua parola este la fel ca parola curenta");
                return null;}
            else
            {
                currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPass));

                this.userRepository.save(currentUser);
                System.out.println("Password change");
            }
        } else
        // Parola curenta nu se potriveste
        {
            System.out.println("Parola curenta nu se potriveste");
            return null;}


        return currentUser;
    }
    public User forgotPass(String email) {
        User currentUser = userRepository.findByEmailUser(email);
        if(currentUser == null)
            return null;
        System.out.println(currentUser);
        String newParola = "parola2";

        currentUser.setPassword(this.bCryptPasswordEncoder.encode(newParola));
        this.userRepository.save(currentUser);
        System.out.println("Password reset");
        return currentUser;
    }

}
