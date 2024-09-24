package com.razahdev.MajuMundurClothing.entities;

import com.razahdev.MajuMundurClothing.constants.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.REWARD)
@Builder
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "reward_id", nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "reward_name")
    private String rewardName;

    @Column(name = "required_points")
    private Integer requiredPoints;
}
