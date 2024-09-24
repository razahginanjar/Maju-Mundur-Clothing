package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CreateTransactionRewardRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateTransactionRewardRequest;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionRewardResponse;
import com.razahdev.MajuMundurClothing.entities.TransactionReward;

import java.util.List;

public interface TransactionRewardService {
    TransactionReward create(CreateTransactionRewardRequest request);
    TransactionReward update(UpdateTransactionRewardRequest request);
    List<TransactionReward> getAll();
    TransactionReward getById(String id);

    TransactionRewardResponse createResponse(CreateTransactionRewardRequest request);
    TransactionRewardResponse updateResponse(UpdateTransactionRewardRequest request);
    List<TransactionRewardResponse> getAllResponses();
    TransactionRewardResponse getByIdResponse(String id);
}
