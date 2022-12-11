package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productRepository.findAll().size(); i++) {
            {
                products.add(productRepository.findAll().get(i));
            }
        }
        return products;
    }

    public Product addProduct(Product product)
    {
         productRepository.save(product);
         return product;
    }

    public Optional<Product> getProduct(Long id)
    {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id)
    {
        Optional<Product> productFound = productRepository.findById(id);
        if(productFound.isPresent())
        {
            System.out.println(productFound.get());
            productRepository.delete(productFound.get());
        }
    }

    public String searchProduct(String nameProduct){
        Product product = productRepository.findByProductName(nameProduct);
        if(product != null)
        {
            return "Produsul a fost gasit";
        }
        else{
            return "Nu exista acest produs";
        }
    }
}
