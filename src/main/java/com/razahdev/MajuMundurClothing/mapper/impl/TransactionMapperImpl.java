package com.razahdev.MajuMundurClothing.mapper.impl;

import com.razahdev.MajuMundurClothing.dto.responses.TransactionResponse;
import com.razahdev.MajuMundurClothing.entities.Transaction;
import com.razahdev.MajuMundurClothing.mapper.ClothMapper;
import com.razahdev.MajuMundurClothing.mapper.CustomerMapper;
import com.razahdev.MajuMundurClothing.mapper.MerchantMapper;
import com.razahdev.MajuMundurClothing.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionMapperImpl implements TransactionMapper {
    private final CustomerMapper customerMapper;
    private final ClothMapper clothMapper;

    @Override
    public TransactionResponse map(Transaction transaction) {
        return TransactionResponse.builder()
                .billId(transaction.getBillId())
                .cloth(clothMapper.map(transaction.getCloth()))
                .transactionDate(transaction.getTransactionDate())
                .customer(customerMapper.map(transaction.getCustomer()))
                .build();
    }
}
