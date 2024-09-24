package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.TransactionRequest;
import com.razahdev.MajuMundurClothing.dto.requests.TransactionRewardRequest;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionRewardResponse;
import com.razahdev.MajuMundurClothing.entities.Transaction;
import com.razahdev.MajuMundurClothing.entities.TransactionReward;

import java.util.List;

public interface TransactionRewardService {
    TransactionReward create(TransactionRewardRequest request);
    TransactionReward update(TransactionRewardRequest request);
    List<TransactionReward> getAll();
    TransactionReward getById(String id);

    TransactionRewardResponse createResponse(TransactionRewardRequest request);
    TransactionRewardResponse updateResponse(TransactionRewardRequest request);
    List<TransactionRewardResponse> getAllResponses();
    TransactionRewardResponse getByIdResponse(String id);
}
