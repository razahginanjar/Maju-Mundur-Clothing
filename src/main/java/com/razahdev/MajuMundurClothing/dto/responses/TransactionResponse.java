package com.razahdev.MajuMundurClothing.dto.responses;

import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.entities.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private String billId;
    private LocalDateTime transactionDate;
    private CustomerResponse customer;
    private ClothResponse cloth;
}
