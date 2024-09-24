package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.dto.requests.CreateCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.CreateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdatePointsCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionResponse;
import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Transaction;
import com.razahdev.MajuMundurClothing.mapper.impl.TransactionMapperImpl;
import com.razahdev.MajuMundurClothing.repository.TransactionRepository;
import com.razahdev.MajuMundurClothing.services.CustomerService;
import com.razahdev.MajuMundurClothing.services.TransactionService;
import com.razahdev.MajuMundurClothing.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final ClothServiceImpl clothService;
    private final ValidationUtils validationUtils;
    private final TransactionMapperImpl transactionMapperImpl;
    private final UsersServiceImpl usersServiceImpl;

    @Override
    public Transaction create(CreateTransactionRequest request) {
        validationUtils.validate(request);
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());

        Cloth cloth = clothService.getById(request.getIdCloth());
        transaction.setCloth(cloth);

        Customer customer = customerService.getByUser();
        transaction.setCustomer(customer);
        UpdatePointsCustomerRequest build = UpdatePointsCustomerRequest.builder().points(customer.getPoints() + 20).build();
        customerService.updatePoints(build);

        return transactionRepository.saveAndFlush(transaction);
    }

    @Override
    public Transaction update(UpdateTransactionRequest request) {
        validationUtils.validate(request);
        Transaction byId = getById(request.getBillId());
        if(!byId.getCustomer().getUsersCustomer().getUserId().equals(usersServiceImpl.getByContext().getUserId()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
        }
        byId.setCloth(clothService.getById(request.getIdCloth()));
        return transactionRepository.saveAndFlush(byId);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getByCloth(String cloth) {
        Cloth byName = clothService.getByName(cloth);
        return transactionRepository.findAllByCloth(byName);
    }

    @Override
    public Transaction getById(String id) {
        if(Objects.isNull(id))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return transactionRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public TransactionResponse createResponse(CreateTransactionRequest request) {
        Transaction transaction = create(request);
        return transactionMapperImpl.map(transaction);
    }

    @Override
    public TransactionResponse updateResponse(UpdateTransactionRequest request) {
        Transaction update = update(request);
        return transactionMapperImpl.map(update);
    }

    @Override
    public List<TransactionResponse> getAllResponses() {
        return getAll().stream().map(
                transactionMapperImpl::map
        ).toList();
    }

    @Override
    public List<TransactionResponse> getByClothResponses(String cloth) {
        return getByCloth(cloth).stream().map(
                transactionMapperImpl::map
        ).toList();
    }

    @Override
    public TransactionResponse getByIdResponse(String id) {
        Transaction byId = getById(id);
        return transactionMapperImpl.map(byId);
    }
}
