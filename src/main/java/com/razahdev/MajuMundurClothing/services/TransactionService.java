package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CreateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionResponse;
import com.razahdev.MajuMundurClothing.entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction create(CreateTransactionRequest request);
    Transaction update(UpdateTransactionRequest request);
    List<Transaction> getAll();
    List<Transaction> getByCloth(String cloth);
    Transaction getById(String id);

    TransactionResponse createResponse(CreateTransactionRequest request);
    TransactionResponse updateResponse(UpdateTransactionRequest request);
    List<TransactionResponse> getAllResponses();
    List<TransactionResponse> getByClothResponses(String cloth);
    TransactionResponse getByIdResponse(String id);
}
