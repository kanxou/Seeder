package com.growthcapital.seeder.controller;

import com.growthcapital.seeder.dto.CashkickDto;
import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.dto.PaymentDto;
import com.growthcapital.seeder.dto.UserDto;
import com.growthcapital.seeder.service.CashkickService;
import com.growthcapital.seeder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final CashkickService cashkickService;

    @Autowired
    public UserController(UserService userService, CashkickService cashkickService) {
        this.userService = userService;
        this.cashkickService = cashkickService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto createdUser = userService.createUser(userDto);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/credit/{credit_amount}")
    public ResponseEntity<List<UserDto>> getUserByCredit(@PathVariable("credit_amount") BigDecimal creditAmount) {
        try {
            List<UserDto> userDtos = userService.getUsersByCreditGreaterThan(creditAmount);
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "user_id") Integer userId) {
        try {
            UserDto userDto = userService.getUserById(userId);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{userId}/contracts")
    public ResponseEntity<List<ContractDto>> getContractsByUserId(@PathVariable Integer userId) {
        try {
            List<ContractDto> contractDTOs = userService.getContractsByUserId(userId);
            return new ResponseEntity<>(contractDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{user_id}/cashkicks")
    public ResponseEntity<List<CashkickDto>> getCashkicksByUserId(@PathVariable("user_id") Integer userId) {
        try {
            List<CashkickDto> cashkicks = cashkickService.getCashkicksByUserId(userId);
            if (cashkicks.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(cashkicks);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{userId}/payments")
    public ResponseEntity<List<PaymentDto>> getAllPaymentsByUser(@PathVariable("userId") Integer userId) {
        try {
            List<PaymentDto> payments = cashkickService.getAllPaymentsByUser(userId);
            if (payments.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            } else {
                return ResponseEntity.ok(payments);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/{userId}/cashkicks")
    public ResponseEntity<String> createCashkickForUser(@RequestBody CashkickDto cashkickDto, @PathVariable Integer userId) {
        try {
            userService.createCashkickForUser(cashkickDto, userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
