package com.razahdev.MajuMundurClothing.repository;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String>{
    Optional<Merchant> findMerchantByUsersMerchant(Users usersMerchant);
}
