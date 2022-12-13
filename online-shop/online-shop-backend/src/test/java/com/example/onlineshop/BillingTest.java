package com.example.onlineshop;

import com.example.onlineshop.security.models.BillingData;
import com.example.onlineshop.security.services.BillingDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class BillingTest {
    private BillingData mockBill = new BillingData();

    @Autowired
    private BillingDataService billingDataService;

    @Test
    public void addBill(){
        billingDataService.addBillingData(mockBill);
    }
}
