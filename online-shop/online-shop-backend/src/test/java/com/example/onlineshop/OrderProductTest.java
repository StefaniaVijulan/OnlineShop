package com.example.onlineshop;

import com.example.onlineshop.security.models.*;
import com.example.onlineshop.security.services.BillingDataService;
import com.example.onlineshop.security.services.OrderProductService;
import com.example.onlineshop.security.services.ProductService;
import com.example.onlineshop.security.services.UserService;
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
public class OrderProductTest {
    private OrderProduct mockOrderProduct = new OrderProduct();

    private Product mockProduct = new Product();

    private User mockUser = new User();

    private User mockUser2 = new User();

    private BillingData mockBillingData = new BillingData();

    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private BillingDataService billingDataService;

    @Before
    public void setUp(){
        mockProduct.setProductName("test");
        mockProduct.setDescription("test");
        mockBillingData.setCity("test");
        mockUser.setLastName("test");
        mockUser.setFirstName("test");
        mockUser.setPassword("password");
        mockUser2.setLastName("testt");
        mockUser2.setFirstName("testt1");
        mockUser2.setPassword("passwordt");
        mockUser.setEmail("teeest");
        mockOrderProduct.setProduct(mockProduct);
        mockOrderProduct.setClient(mockUser);
        mockOrderProduct.setQuantity(1);
        mockOrderProduct.setFinalized(false);
        mockOrderProduct.setBillingData(mockBillingData);
    }

    @Test
    public void addOrder() throws Exception {
        billingDataService.addBillingData(mockBillingData);
        productService.addProduct(mockProduct);
        userService.registerUser(mockUser);
        orderProductService.addOrder(mockOrderProduct);

        for(OrderProduct op: orderProductService.getProductsOrderedByClientId(1L)){
            assert op.getQuantity() == 1;
        }
    }

    @Test
    public void updateOrder() throws Exception {
        billingDataService.addBillingData(mockBillingData);
        productService.addProduct(mockProduct);
        userService.registerUser(mockUser2);
        mockOrderProduct.setClient(mockUser2);
        orderProductService.addOrder(mockOrderProduct);
        orderProductService.updateOrderProduct(mockOrderProduct, true);

        for(OrderProduct op: orderProductService.getProductsOrderedByDesignerId(1L)){
            assert op.getFinalized();
        }
    }
}
