package com.growthcapital.seeder.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 45)
    private String password; 

    @Column(name = "term_cap", nullable = false)
    private Integer termCap;

    @Column(name = "available_credit", nullable = false, precision = 10, scale = 2)
    private BigDecimal availableCredit;

    @Column(name = "max_interest_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxInterestRate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Cashkick> cashkicks;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserContract> userContracts;

}
