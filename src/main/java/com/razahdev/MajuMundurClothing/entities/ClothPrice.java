package com.razahdev.MajuMundurClothing.entities;

import com.razahdev.MajuMundurClothing.constants.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ConstantTable.CLOTH_PRICE)
@Builder
public class ClothPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long price;

    @OneToMany(mappedBy = "clothPrice")
    private List<Cloth> cloths;
}
