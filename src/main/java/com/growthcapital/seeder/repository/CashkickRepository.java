package com.growthcapital.seeder.repository;

import com.growthcapital.seeder.entity.Cashkick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CashkickRepository extends JpaRepository<Cashkick, Long> {
    Optional<List<Cashkick>> getAllByUserUserId(Integer userId);

}
