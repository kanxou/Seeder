package com.growthcapital.seeder.service;

import com.growthcapital.seeder.dto.CashkickDto;
import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.dto.UserDto;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    UserDto getUserById(Integer userId);
    List<ContractDto> getContractsByUserId(Integer userId);
    UserDto createUser(UserDto userDto);
    void createCashkickForUser(CashkickDto cashkickDto, Integer userId);
    List<UserDto> getUsersByCreditGreaterThan(BigDecimal creditAmount);
}