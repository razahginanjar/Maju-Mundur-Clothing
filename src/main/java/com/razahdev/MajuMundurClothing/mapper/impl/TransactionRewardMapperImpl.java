package com.razahdev.MajuMundurClothing.mapper.impl;

import com.razahdev.MajuMundurClothing.dto.responses.CustomerResponse;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionRewardResponse;
import com.razahdev.MajuMundurClothing.entities.TransactionReward;
import com.razahdev.MajuMundurClothing.mapper.CustomerMapper;
import com.razahdev.MajuMundurClothing.mapper.RewardMapper;
import com.razahdev.MajuMundurClothing.mapper.TransactionRewardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionRewardMapperImpl implements TransactionRewardMapper {
    private final CustomerMapper customerMapper;
    private final RewardMapper rewardMapper;

    @Override
    public TransactionRewardResponse map(TransactionReward transactionReward) {
        return TransactionRewardResponse.builder()
                .id(transactionReward.getId())
                .reward(rewardMapper.map(transactionReward.getReward()))
                .customer(customerMapper.map(transactionReward.getCustomer()))
                .transactionDate(transactionReward.getTransactionDate())
                .build();
    }
}
