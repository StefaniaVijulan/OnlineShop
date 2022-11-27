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

    private Product mockProduct;

    @Autowired
    private ProductService productService;

    @Before
    public void setUp(){
        mockProduct.setProductName("test");
        mockProduct.setId(121L);
        mockProduct.setPrice(100);
    }

    @Test
    public void add(){
        productService.addProduct(mockProduct);

        assert productService.getProduct(121L).isPresent();
    }

    @Test
    public void add_delete(){
        productService.addProduct(mockProduct);

        productService.deleteProduct(121L);

        assert productService.getProduct(121L).isEmpty();
    }

}
