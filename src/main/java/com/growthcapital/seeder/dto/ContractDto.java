package com.growthcapital.seeder.dto;

import com.growthcapital.seeder.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {

    private Integer contractId;
    private String contractName;
    private String type;
    private BigDecimal perPayment;
    private Integer termLength;
    private BigDecimal interestRate;
    private BigDecimal paymentAmount;
    private ContractStatus contractStatus;
}