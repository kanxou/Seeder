package com.growthcapital.seeder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentData{
    private BigDecimal payoutAmount;
    private BigDecimal interestRate;
    private Integer term;
}
