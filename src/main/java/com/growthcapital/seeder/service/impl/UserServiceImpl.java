package com.growthcapital.seeder.service.impl;

import com.growthcapital.seeder.dto.CashkickDto;
import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.dto.UserDto;
import com.growthcapital.seeder.entity.User;
import com.growthcapital.seeder.exception.ResourceNotFoundException;
import com.growthcapital.seeder.mapper.UserContractMapper;
import com.growthcapital.seeder.mapper.UserMapper;
import com.growthcapital.seeder.repository.UserContractRepository;
import com.growthcapital.seeder.repository.UserRepository;
import com.growthcapital.seeder.service.CashkickService;
import com.growthcapital.seeder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserContractRepository userContractRepository;
  private final CashkickService cashkickService;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserContractRepository userContractRepository, CashkickService cashkickService) {
    this.userRepository = userRepository;
    this.userContractRepository = userContractRepository;
    this.cashkickService = cashkickService;
  }

  @Override
  public UserDto createUser(UserDto userDto) {
    User user = UserMapper.toEntity(userDto);
    User savedUser = userRepository.save(user);
    return UserMapper.toDto(savedUser);
  }

  @Override
  @Transactional
  public void createCashkickForUser(CashkickDto cashkickDto, Integer userId) {
    User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
    cashkickService.createCashkick(cashkickDto, user);
    userRepository.save(user);
  }

  @Override
  public List<UserDto> getUsersByCreditGreaterThan(BigDecimal creditAmount) {
    List<User> users = userRepository.getAllByAvailableCreditGreaterThan(creditAmount);
    List<UserDto> userDtos = users.stream().map(UserMapper::toDto).toList();
    return userDtos;
  }

  @Override
  public UserDto getUserById(Integer userId) {
    return userRepository.findById(userId).map(UserMapper::toDto).orElseThrow(() -> new ResourceNotFoundException("User not found"));
  }

  @Override
  public List<ContractDto> getContractsByUserId(Integer userId) {
    return userContractRepository.getAllByUserUserId(userId)
            .map(userContracts -> userContracts.stream()
                    .map(UserContractMapper::toDto)
                    .toList())
            .orElse(Collections.emptyList());
  }

}