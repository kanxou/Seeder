package com.growthcapital.seeder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "user_contract")
@Getter
@Setter
@NoArgsConstructor
public class UserContract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_contract_id")
  private Integer userContractId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "contract_id", nullable = false)
  private Contract contract;

  @Column(name = "payment_amount", nullable = false, precision = 15, scale = 2)
  private BigDecimal paymentAmount;

  @Column(name = "per_payment", nullable = false, precision = 15, scale = 2)
  private BigDecimal perPayment;
}
