package com.growthcapital.seeder.service.impl;

import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.entity.Contract;
import com.growthcapital.seeder.entity.User;
import com.growthcapital.seeder.entity.UserContract;
import com.growthcapital.seeder.enums.ContractStatus;
import com.growthcapital.seeder.exception.InvalidOperationException;
import com.growthcapital.seeder.exception.ResourceNotFoundException;
import com.growthcapital.seeder.mapper.ContractMapper;
import com.growthcapital.seeder.mapper.UserContractMapper;
import com.growthcapital.seeder.repository.ContractRepository;
import com.growthcapital.seeder.repository.UserContractRepository;
import com.growthcapital.seeder.service.ContractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final UserContractRepository userContractRepository;

    private static final Logger logger = LogManager.getLogger(ContractServiceImpl.class);

    public ContractServiceImpl(ContractRepository contractRepository, UserContractRepository userContractRepository) {
        this.contractRepository = contractRepository;
        this.userContractRepository = userContractRepository;
    }

    @Override
    public List<ContractDto> getAllContracts() {
        return contractRepository.findAll().stream().map(ContractMapper::toDto).toList();
    }

    @Override
    public List<ContractDto> getAllAvailableContracts() {
        return contractRepository.findAllByContractStatus(ContractStatus.AVAILABLE).stream().map(ContractMapper::toDto).toList();
    }

    @Override
    public void addContracts(List<ContractDto> contractDTOS) {
        List<Contract> contracts = contractDTOS.stream()
                .map(ContractMapper::toEntity).toList();

        contractRepository.saveAll(contracts);
    }

    @Override
    public void validateAndSaveUserContracts(List<ContractDto> contractDtos, User user) {
        boolean areContractsValid = contractDtos.stream().allMatch(this::validateContract);
        logger.info("Are the contracts valid : {}", areContractsValid);
        if(areContractsValid){
            List<Integer> contractIds = contractDtos.stream()
                    .map(ContractDto::getContractId)
                    .toList();
            List<Contract> contracts = contractRepository.findAllById(contractIds);
            for(ContractDto contractDto: contractDtos){
                Contract contract = contracts.stream().filter(c-> c.getContractId().equals(contractDto.getContractId())).findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException("Contract with id : " + contractDto.getContractId() + " does not exist"));
                contract.setPaymentAmount(contract.getPaymentAmount().subtract(contractDto.getPaymentAmount()));
                if(contract.getPaymentAmount().compareTo(new BigDecimal(0))==0)
                    contract.setContractStatus(ContractStatus.UNAVAILABLE);
                UserContract userContract = UserContractMapper.toEntity(contractDto, contract , user);
                userContractRepository.save(userContract);
            }
            contractRepository.saveAll(contracts);
        }
    }

    public boolean validateContract(ContractDto contractDto) {
        Integer contractId = contractDto.getContractId();
        BigDecimal requestedPaymentAmount = contractDto.getPaymentAmount();

        return contractRepository.findById(contractId)
                .filter(contract -> requestedPaymentAmount.compareTo(contract.getPaymentAmount()) <= 0)
                .map(contract -> true)
                .orElseThrow(()->new InvalidOperationException("Contract details are invalid"));
    }
}
