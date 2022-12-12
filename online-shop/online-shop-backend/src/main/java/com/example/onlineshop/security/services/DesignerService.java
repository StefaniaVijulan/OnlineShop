package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.ChangeImg;
import com.example.onlineshop.security.models.Designer;
import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.repositories.DesignerRepository;
import com.example.onlineshop.security.repositories.ProductRepository;
import com.example.onlineshop.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DesignerService{


    @Autowired
    private DesignerRepository designerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Designer loginDesigner(String emailDesigner, String password){
        System.out.println("Intra in login designer");
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
            //designerul exista
            return null;
        }

        if(designer.getImageDesigner() == null || designer.getImageDesigner().trim().isEmpty()){
            designer.setImageDesigner("https://media.istockphoto.com/id/1300845620/vector/user-icon-flat-isolated-on-white-background-user-symbol-vector-illustration.jpg?s=612x612&w=0&k=20&c=yBeyba0hUkh14_jgv1OKqIH0CCSWU_4ckRkAoy2p73o=");
        }
        designer.setPasswordDesigner(bCryptPasswordEncoder.encode(designer.getPasswordDesigner()));

        designer.setRole("DESIGNER");
        designerRepository.save(designer);
        return designer;
    }

    public List<Designer> getDesigners() {
        List<Designer> designers = new ArrayList<>();
        for (int i = 0; i < designerRepository.findAll().size(); i++) {
            {
                designers.add(designerRepository.findAll().get(i));
            }
        }
        return designers;
    }

    public List<Product> getAllMyProducts(Long myId){
        List<Product> products = new ArrayList<>();
        for(int i=0; i< productRepository.findAll().size(); i++){
            if(productRepository.findAll().get(i).getDesigner().getId().equals(myId)){
                if(!productRepository.findAll().get(i).getStatus().equals("refuzat")){
                    products.add(productRepository.findAll().get(i));
                }
            }
        }
        return products;
    }

    public Product editStatusProduct(Long productId, String newStatus){
        for(int i=0; i<productRepository.findAll().size(); i++)
            if(productRepository.findAll().get(i).getId().equals(productId)) {
                productRepository.findAll().get(i).setStatus(newStatus);
                productRepository.save(productRepository.findAll().get(i));
                return productRepository.findAll().get(i);

            }
        return null;
    }

    public Designer changeImage(String email, ChangeImg changeImg){
        Designer designer = designerRepository.findByEmail(email);
        if(changeImg.getImageUser() == null){
            return null;
        }
        else{
            designer.setImageDesigner(changeImg.getImageUser());
            designerRepository.save(designer);
        }
        return designer;
    }
}
