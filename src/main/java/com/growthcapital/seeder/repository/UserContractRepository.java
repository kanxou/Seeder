package com.growthcapital.seeder.repository;

import com.growthcapital.seeder.entity.UserContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserContractRepository extends JpaRepository<UserContract, Integer> {
    Optional<List<UserContract>> getAllByUserUserId(Integer userId);
}
