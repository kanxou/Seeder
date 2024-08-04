package com.growthcapital.seeder.service;

import com.growthcapital.seeder.dto.CashkickDto;
import com.growthcapital.seeder.dto.PaymentDto;
import com.growthcapital.seeder.entity.User;

import java.util.List;

public interface CashkickService {

    void createCashkick(CashkickDto cashkickDto, User user);

    List<CashkickDto> getCashkicksByUserId(Integer userId);

    List<PaymentDto> getAllPaymentsByUser(Integer userId);
}
