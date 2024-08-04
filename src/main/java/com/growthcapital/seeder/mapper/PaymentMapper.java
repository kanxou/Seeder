package com.growthcapital.seeder.mapper;

import com.growthcapital.seeder.dto.PaymentDto;
import com.growthcapital.seeder.entity.Payment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentMapper {

    public static PaymentDto toDto(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(payment.getPaymentId());
        paymentDto.setCashkickId(payment.getCashkick().getCashkickId()); 
        paymentDto.setDueDate(payment.getDueDate());
        paymentDto.setExpectedAmount(payment.getExpectedAmount());
        paymentDto.setOutstandingAmount(payment.getOutstandingAmount());
        paymentDto.setStatus(payment.getStatus());

        return paymentDto;
    }
}