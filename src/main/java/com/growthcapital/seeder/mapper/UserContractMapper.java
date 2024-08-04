package com.growthcapital.seeder.mapper;

import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.entity.Contract;
import com.growthcapital.seeder.entity.User;
import com.growthcapital.seeder.entity.UserContract;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserContractMapper {

  public static ContractDto toDto(UserContract userContract) {
    if (userContract == null) {
      return null;
    }

    ContractDto contractDto = new ContractDto();
    contractDto.setContractId(userContract.getContract().getContractId());
    contractDto.setContractName(userContract.getContract().getContractName());
    contractDto.setType(userContract.getContract().getType().getValue());
    contractDto.setTermLength(userContract.getContract().getTermLength());
    contractDto.setInterestRate(userContract.getContract().getInterestRate());
    contractDto.setPerPayment(userContract.getPerPayment());
    contractDto.setPaymentAmount(userContract.getPaymentAmount());
    contractDto.setContractStatus(userContract.getContract().getContractStatus());

    return contractDto;
  }

  public static UserContract toEntity(ContractDto contractDto, Contract contract, User user) {
    if (contractDto == null) {
      return null;
    }

    UserContract userContract = new UserContract();
    userContract.setPerPayment(contractDto.getPerPayment());
    userContract.setPaymentAmount(contractDto.getPaymentAmount());
    userContract.setContract(contract);
    userContract.setUser(user);

    return userContract;
  }
}