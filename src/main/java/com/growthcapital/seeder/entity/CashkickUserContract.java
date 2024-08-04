package com.growthcapital.seeder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cashkick_usercontract", uniqueConstraints = @UniqueConstraint(columnNames = {"cashkick_id", "user_contract_id"}))
public class CashkickUserContract {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cashkick_id", nullable = false)
  private Cashkick cashkick;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_contract_id", nullable = false)
  private UserContract userContract;

}
