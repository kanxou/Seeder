package com.growthcapital.seeder.dto;

import com.growthcapital.seeder.enums.CashkickStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CashkickDto {

    private Integer cashkickId;
    private String cashkickName;
    private CashkickStatus status;
    private LocalDate maturity;
    private Integer term;
    private BigDecimal totalPayout;
    private BigDecimal paybackAmount;
    private List<ContractDto> contracts;

}