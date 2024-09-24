package com.razahdev.MajuMundurClothing.mapper;

import com.razahdev.MajuMundurClothing.dto.responses.TransactionRewardResponse;
import com.razahdev.MajuMundurClothing.entities.TransactionReward;

public interface TransactionRewardMapper {
    TransactionRewardResponse map(TransactionReward transactionReward);
}
