package com.example.onlineshop.security.repositories;

import com.example.onlineshop.security.models.BillingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingDataRepository extends JpaRepository<BillingData, Long> {
}
