package com.razahdev.MajuMundurClothing.repository;

import com.razahdev.MajuMundurClothing.entities.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, String> {
    Optional<Cloth> findClothByName(String name);
    Boolean existsByName(String name);
}
