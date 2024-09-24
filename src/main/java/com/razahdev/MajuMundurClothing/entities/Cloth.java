package com.razahdev.MajuMundurClothing.entities;

import com.razahdev.MajuMundurClothing.constants.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.CLOTH)
@Builder
public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "clothing_name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cloth_price_id")
    private ClothPrice clothPrice;
}
