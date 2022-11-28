package com.example.onlineshop;

import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class ProductTest {

    private Product mockProduct = new Product();

    @Autowired
    private ProductService productService;

    @Before
    public void setUp(){
        mockProduct.setProductName("test");
        mockProduct.setPrice(100);
    }

    @Test
    public void add(){
        Product addedProduct = productService.addProduct(mockProduct);

        assert productService.getProduct(addedProduct.getId()).isPresent();
    }

    @Test
    public void add_delete(){
        Product addedProduct = productService.addProduct(mockProduct);

        productService.addProduct(mockProduct);

        productService.deleteProduct(addedProduct.getId());

        assert productService.getProduct(addedProduct.getId()).isEmpty();
    }

}
