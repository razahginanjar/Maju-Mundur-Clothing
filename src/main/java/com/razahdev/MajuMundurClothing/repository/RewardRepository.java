package com.razahdev.MajuMundurClothing.repository;
import com.razahdev.MajuMundurClothing.entities.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<Reward, String> {
    Optional<Reward> findByRewardName(String name);
}
