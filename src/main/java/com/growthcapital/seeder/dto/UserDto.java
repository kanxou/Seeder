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
public class UserDto {

    private Integer userId;
    private String username;
    private String email;
    private Integer termCap;
    private String password;
    private BigDecimal availableCredit;
    private BigDecimal maxInterestRate;
}