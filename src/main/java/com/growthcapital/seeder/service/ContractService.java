package com.growthcapital.seeder.service;

import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.entity.User;

import java.util.List;

public interface ContractService {
    List<ContractDto> getAllContracts();
    List<ContractDto> getAllAvailableContracts();
    void addContracts(List<ContractDto> contractDTOS);
    void validateAndSaveUserContracts(List<ContractDto> contractDtos, User user);
}
