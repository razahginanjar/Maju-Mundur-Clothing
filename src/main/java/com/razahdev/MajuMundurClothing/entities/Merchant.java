package com.razahdev.MajuMundurClothing.entities;

import com.razahdev.MajuMundurClothing.constants.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.MERCHANT)
@Builder
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "merchant_id", nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "merchant_name", nullable = false, unique = true)
    private String merchantName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users usersMerchant;

}