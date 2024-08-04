package com.growthcapital.seeder.repository;

import com.growthcapital.seeder.entity.Contract;
import com.growthcapital.seeder.enums.ContractStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findAllByContractStatus(ContractStatus status);
}
