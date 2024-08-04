package com.growthcapital.seeder.entity;

import com.growthcapital.seeder.enums.ContractStatus;
import com.growthcapital.seeder.enums.ContractType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "contract")
@Getter
@Setter
@NoArgsConstructor
public class Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contract_id")
  private Integer contractId;

  @Column(name = "contract_name", nullable = false, length = 255)
  private String contractName;

  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false, length = 10)
  private ContractType type;

  @Column(name = "per_payment", nullable = false, precision = 10, scale = 2)
  private BigDecimal perPayment;

  @Column(name = "term_length", nullable = false)
  private Integer termLength;

  @Column(name = "interest_rate", nullable = false, precision = 5, scale = 2)
  private BigDecimal interestRate;

  @Column(name = "payment_amount", nullable = false, precision = 15, scale = 2)
  private BigDecimal paymentAmount;

  @Enumerated
  @Column(name = "contract_status", nullable = false, length = 20)
  private ContractStatus contractStatus;

}
