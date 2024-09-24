package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.RewardRequest;
import com.razahdev.MajuMundurClothing.dto.requests.TransactionRequest;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionResponse;
import com.razahdev.MajuMundurClothing.entities.Reward;
import com.razahdev.MajuMundurClothing.entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction create(TransactionRequest request);
    Transaction update(TransactionRequest request);
    List<Transaction> getAll();
    List<Transaction> getByCloth(String cloth);
    Transaction getById(String id);

    TransactionResponse createResponse(TransactionRequest request);
    TransactionResponse updateResponse(TransactionRequest request);
    List<TransactionResponse> getAllResponses();
    List<TransactionResponse> getByClothResponses(String cloth);
    TransactionResponse getByIdResponse(String id);
}
