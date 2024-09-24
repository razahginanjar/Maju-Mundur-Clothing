package com.razahdev.MajuMundurClothing.repository;

import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRoleRepository extends JpaRepository<UsersRoles, String> {
    Optional<UsersRoles> findByRole(ConstantRole role);
}
