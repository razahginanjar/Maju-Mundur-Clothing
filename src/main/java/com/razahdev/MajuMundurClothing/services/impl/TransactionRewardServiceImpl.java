package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.dto.requests.TransactionRewardRequest;
import com.razahdev.MajuMundurClothing.entities.TransactionReward;
import com.razahdev.MajuMundurClothing.repository.TransactionRewardRepository;
import com.razahdev.MajuMundurClothing.services.CustomerService;
import com.razahdev.MajuMundurClothing.services.RewardService;
import com.razahdev.MajuMundurClothing.services.TransactionRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionRewardServiceImpl implements TransactionRewardService {
    private final TransactionRewardRepository transactionRewardRepository;
    private final CustomerService customerService;
    private final RewardService rewardService;


    @Override
    public TransactionReward create(TransactionRewardRequest request) {
        return null;
    }

    @Override
    public TransactionReward update(TransactionRewardRequest request) {
        return null;
    }

    @Override
    public List<TransactionReward> getAll() {
        return List.of();
    }

    @Override
    public TransactionReward getById(String id) {
        return null;
    }
}
