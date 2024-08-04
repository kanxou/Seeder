package com.growthcapital.seeder.entity;

import com.growthcapital.seeder.enums.CashkickStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cashkick")
@Getter
@Setter
@NoArgsConstructor
public class Cashkick {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cashkick_id")
  private Integer cashkickId;

  @Column(name = "cashkick_name", nullable = false, length = 255)
  private String cashkickName;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private CashkickStatus status;

  @Column(name = "maturity", nullable = false)
  private LocalDate maturity;

  @Column(name = "term", nullable = false)
  private Integer term;

  @Column(name = "total_payout", nullable = false, precision = 15, scale = 2)
  private BigDecimal totalPayout;

  @Column(name = "payback_amount", nullable = false, precision = 15, scale = 2)
  private BigDecimal paybackAmount;

  @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "cashkick", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Payment> payments;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

}
