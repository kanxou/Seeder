package com.growthcapital.seeder.service.impl;

import com.growthcapital.seeder.dto.PaymentData;
import com.growthcapital.seeder.entity.Cashkick;
import com.growthcapital.seeder.entity.Payment;
import com.growthcapital.seeder.enums.PaymentStatus;
import com.growthcapital.seeder.exception.ResourceNotFoundException;
import com.growthcapital.seeder.repository.PaymentRepository;
import com.growthcapital.seeder.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> generatePaymentsForCashkick(Cashkick cashkick) {
        List<Payment> payments = new ArrayList<>();

        Integer termLengthMonths = cashkick.getTerm();
        BigDecimal totalExpectedAmount = cashkick.getPaybackAmount();
        BigDecimal monthlyPaymentAmount = totalExpectedAmount.divide(BigDecimal.valueOf(termLengthMonths), 2, RoundingMode.HALF_UP);

        LocalDate currentDate = LocalDate.now();
        BigDecimal remainingAmount = totalExpectedAmount;

        for (int i = 0; i < termLengthMonths; i++) {
            LocalDate dueDate = currentDate.plusMonths(i + 1L);
            BigDecimal currentPaymentAmount = (i == termLengthMonths - 1) ? remainingAmount : monthlyPaymentAmount;

            Payment payment = new Payment(null , cashkick, dueDate, currentPaymentAmount, remainingAmount.subtract(currentPaymentAmount),PaymentStatus.UPCOMING);
            payments.add(payment);
            remainingAmount = remainingAmount.subtract(currentPaymentAmount);
        }

        return payments;
    }

    @Override
    public BigDecimal calculatePaybackAmount(PaymentData paymentData) {
        return paymentData.getPayoutAmount().multiply(paymentData.getInterestRate()).multiply(BigDecimal.valueOf(paymentData.getTerm()/12));
    }

    @Override
    public void updateStatus(Integer paymentId, PaymentStatus paymentStatus) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("Payment with id "+ paymentId+" does not exist"));
        if(!payment.getStatus().equals(paymentStatus)){
            payment.setStatus(paymentStatus);
        }
        paymentRepository.save(payment);
    }
}
