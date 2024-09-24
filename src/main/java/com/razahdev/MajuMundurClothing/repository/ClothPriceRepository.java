package com.razahdev.MajuMundurClothing.repository;

import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.entities.ClothPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothPriceRepository extends JpaRepository<ClothPrice, String> {
    Optional<ClothPrice> findClothPriceByPrice(Long price);
}
