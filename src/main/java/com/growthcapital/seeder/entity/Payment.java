package com.growthcapital.seeder.entity;

import com.growthcapital.seeder.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_id")
  private Integer paymentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cashkick_id", nullable = false)
  private Cashkick cashkick;

  @Column(name = "due_date", nullable = false)
  private LocalDate dueDate;

  @Column(name = "expected_amount", nullable = false, precision = 15, scale = 2)
  private BigDecimal expectedAmount;

  @Column(name = "outstanding_amount", nullable = false, precision = 15, scale = 2)
  private BigDecimal outstandingAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 50)
  private PaymentStatus status;
}
