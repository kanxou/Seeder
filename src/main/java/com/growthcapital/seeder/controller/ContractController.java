package com.growthcapital.seeder.controller;

import com.growthcapital.seeder.dto.ContractDto;
import com.growthcapital.seeder.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contracts")
@CrossOrigin(origins = "*")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAllContracts() {
        try {
            List<ContractDto> contracts = contractService.getAllContracts();
            return new ResponseEntity<>(contracts, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<ContractDto>> getAllAvailableContracts() {
        try {
            List<ContractDto> contracts = contractService.getAllAvailableContracts();
            return new ResponseEntity<>(contracts, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addContracts(@RequestBody List<ContractDto> contractDTOs) {
        try {
            contractService.addContracts(contractDTOs);
            return ResponseEntity.status(HttpStatus.CREATED).body("Contracts added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
