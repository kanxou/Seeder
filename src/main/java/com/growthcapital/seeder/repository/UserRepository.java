package com.growthcapital.seeder.repository;

import com.growthcapital.seeder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getAllByAvailableCreditGreaterThan(BigDecimal credit);
}