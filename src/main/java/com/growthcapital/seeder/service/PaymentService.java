package com.growthcapital.seeder.service;

import com.growthcapital.seeder.dto.PaymentData;
import com.growthcapital.seeder.entity.Cashkick;
import com.growthcapital.seeder.entity.Payment;
import com.growthcapital.seeder.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    List<Payment> generatePaymentsForCashkick(Cashkick cashkick);
    BigDecimal calculatePaybackAmount(PaymentData paymentData);
    void updateStatus(Integer paymentId, PaymentStatus paymentStatus);
}
