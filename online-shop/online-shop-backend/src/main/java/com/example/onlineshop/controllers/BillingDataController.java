package com.example.onlineshop.controllers;

import com.example.onlineshop.security.models.BillingData;
import com.example.onlineshop.security.services.BillingDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/billingData")
@RequiredArgsConstructor
public class BillingDataController {

    @Autowired
    private BillingDataService billingDataService;

    @PostMapping("/save")
    public ResponseEntity<BillingData> addBillingData(@RequestBody BillingData billingData)
    {
        return ResponseEntity.ok(billingDataService.addBillingData(billingData));
    }
}
