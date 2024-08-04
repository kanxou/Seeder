package com.growthcapital.seeder.service.impl;

import com.growthcapital.seeder.dto.CashkickDto;
import com.growthcapital.seeder.dto.PaymentDto;
import com.growthcapital.seeder.entity.Cashkick;
import com.growthcapital.seeder.entity.Payment;
import com.growthcapital.seeder.entity.User;
import com.growthcapital.seeder.mapper.CashkickMapper;
import com.growthcapital.seeder.mapper.PaymentMapper;
import com.growthcapital.seeder.repository.CashkickRepository;
import com.growthcapital.seeder.service.CashkickService;
import com.growthcapital.seeder.service.ContractService;
import com.growthcapital.seeder.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CashkickServiceImpl implements CashkickService {

    private static final Logger logger = LogManager.getLogger(CashkickServiceImpl.class);

    private final CashkickRepository cashkickRepository;

    private final PaymentService paymentService;

    private final ContractService contractService;


    @Autowired
    public CashkickServiceImpl(CashkickRepository cashkickRepository, PaymentService paymentService, ContractService contractService) {
        this.cashkickRepository = cashkickRepository;
        this.paymentService = paymentService;
        this.contractService = contractService;
    }

    @Override
    public void createCashkick(CashkickDto cashkickDto, User user) {
        logger.info("Creating a new Cashkick");
        Cashkick cashkick = CashkickMapper.toEntity(cashkickDto, user);

        contractService.validateAndSaveUserContracts(cashkickDto.getContracts(), user);

        List<Payment> payments = paymentService.generatePaymentsForCashkick(cashkick);
        cashkick.setPayments(payments);

        user.getCashkicks().add(cashkick);
        user.setAvailableCredit(user.getAvailableCredit().subtract(cashkick.getPaybackAmount()));

        cashkickRepository.save(cashkick);
    }

    @Override
    public List<CashkickDto> getCashkicksByUserId(Integer userId) {
        Optional<List<Cashkick>> cashkickListOpt = cashkickRepository.getAllByUserUserId(userId);
        return cashkickListOpt.map(cashkickList ->
                        cashkickList.stream()
                                .map(CashkickMapper::toDto)
                                .toList()
                )
                .orElse(Collections.emptyList());

    }

    @Override
    public List<PaymentDto> getAllPaymentsByUser(Integer userId) {
        return cashkickRepository.getAllByUserUserId(userId)
                .orElse(Collections.emptyList())  
                .stream()
                .flatMap(cashkick -> cashkick.getPayments().stream())  
                .map(PaymentMapper::toDto)  
                .toList();
    }
}
