package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.BillingData;
import com.example.onlineshop.security.repositories.BillingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingDataService {

    @Autowired
    private BillingDataRepository billingDataRepository;

    public BillingData addBillingData(BillingData billingData)
    {
        return billingDataRepository.save(billingData);
    }
}
