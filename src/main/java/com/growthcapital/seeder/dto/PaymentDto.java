package com.growthcapital.seeder.dto;
import com.growthcapital.seeder.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

    private Integer paymentId;
    private Integer cashkickId;
    private LocalDate dueDate;
    private BigDecimal expectedAmount;
    private BigDecimal outstandingAmount;
    private PaymentStatus status;

}