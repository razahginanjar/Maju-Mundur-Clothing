package com.razahdev.MajuMundurClothing.entities;


import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.constants.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.ROLE)
@Builder
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id", nullable = false, updatable = false, unique = true)
    private String roleId;

    @Column(name = "role", nullable = false, updatable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ConstantRole role;

}
