package com.growthcapital.seeder.repository;

import com.growthcapital.seeder.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
}