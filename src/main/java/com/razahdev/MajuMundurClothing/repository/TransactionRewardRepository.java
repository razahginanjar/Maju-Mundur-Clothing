package com.razahdev.MajuMundurClothing.repository;

import com.razahdev.MajuMundurClothing.entities.Transaction;
import com.razahdev.MajuMundurClothing.entities.TransactionReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRewardRepository extends JpaRepository<TransactionReward, String> {
}
