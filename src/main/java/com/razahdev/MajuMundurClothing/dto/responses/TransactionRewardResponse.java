package com.razahdev.MajuMundurClothing.dto.responses;

import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Reward;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRewardResponse {
    private String id;
    private LocalDateTime transactionDate;
    private CustomerResponse customer;
    private RewardResponse reward;
}
