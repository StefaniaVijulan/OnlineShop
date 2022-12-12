package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.models.User;
import com.example.onlineshop.security.repositories.DesignerRepository;
import com.example.onlineshop.security.repositories.ProductRepository;
import com.example.onlineshop.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private DesignerRepository designerRepository;

    @Autowired
    private ProductRepository productRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("++"+email);
        UserDetails user = userRepository.findByEmail(email);
        System.out.println("=>"+ user);

        if(user == null) {
            user = designerRepository.findByEmail((email));
        }
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
        System.out.println(userProfile);
        return userProfile;
    }

    public User registerUser(User user) throws Exception {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (userOptional.isPresent()) {
            throw new IOException("Exista userul deja");
        }
        else
        {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        user.setRole("CLIENT");
        userRepository.saveAndFlush(user);
        return user;
    };
    public User changePassword(String oldPass, String newPass, String cnpC) throws Exception {
        String username;
        username = userRepository.findByEmail(cnpC).getEmail();
        User currentUser = this.userRepository.findByEmail(username);
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
        User currentUser = userRepository.findByEmail(email);
        if(currentUser == null)
            return null;
        System.out.println(currentUser);
        String newParola = "parola2";

        currentUser.setPassword(this.bCryptPasswordEncoder.encode(newParola));
        this.userRepository.save(currentUser);
        System.out.println("Password reset");
        return currentUser;
    }
    public List<Product> getAllMyProducts(Long myId){
        List<Product> products = new ArrayList<>();
        for(int i=0; i< productRepository.findAll().size(); i++){
            if(productRepository.findAll().get(i).getUser().getId().equals(myId)){

                    products.add(productRepository.findAll().get(i));
                }
            }

        return products;
    }



}
