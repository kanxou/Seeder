package com.growthcapital.seeder.mapper;

import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.entity.Contract;
import com.growthcapital.seeder.enums.ContractType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContractMapper {

    public static ContractDto toDto(Contract contract) {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId(contract.getContractId());
        contractDto.setContractName(contract.getContractName());
        contractDto.setType(contract.getType().getValue());
        contractDto.setPerPayment(contract.getPerPayment());
        contractDto.setTermLength(contract.getTermLength());
        contractDto.setInterestRate(contract.getInterestRate());
        contractDto.setPaymentAmount(contract.getPaymentAmount());
        contractDto.setContractStatus(contract.getContractStatus());
        return contractDto;
    }

    public static Contract toEntity(ContractDto contractDto) {
        Contract contract = new Contract();
        contract.setContractId(contractDto.getContractId());
        contract.setContractName(contractDto.getContractName());
        contract.setType(ContractType.valueOf(contractDto.getType().toUpperCase()));
        contract.setPerPayment(contractDto.getPerPayment());
        contract.setTermLength(contractDto.getTermLength());
        contract.setInterestRate(contractDto.getInterestRate());
        contract.setPaymentAmount(contractDto.getPaymentAmount());
        contract.setContractStatus(contractDto.getContractStatus());
        return contract;
    }
}
