package com.razahdev.MajuMundurClothing.entities;

import com.razahdev.MajuMundurClothing.constants.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ConstantTable.TRANSACTION_REWARD)
@Builder
public class TransactionReward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_reward_id")
    private String id;

    @Column(name = "transaction_reward_date")
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;
}
