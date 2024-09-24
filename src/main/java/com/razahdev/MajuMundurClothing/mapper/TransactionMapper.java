package com.razahdev.MajuMundurClothing.mapper;

import com.razahdev.MajuMundurClothing.dto.responses.TransactionResponse;
import com.razahdev.MajuMundurClothing.entities.Transaction;

public interface TransactionMapper {
    TransactionResponse map(Transaction transaction);
}
