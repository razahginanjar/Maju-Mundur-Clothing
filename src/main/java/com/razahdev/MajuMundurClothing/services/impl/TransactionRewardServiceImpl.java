package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.dto.requests.*;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionRewardResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Reward;
import com.razahdev.MajuMundurClothing.entities.TransactionReward;
import com.razahdev.MajuMundurClothing.mapper.impl.TransactionRewardMapperImpl;
import com.razahdev.MajuMundurClothing.repository.TransactionRewardRepository;
import com.razahdev.MajuMundurClothing.services.CustomerService;
import com.razahdev.MajuMundurClothing.services.RewardService;
import com.razahdev.MajuMundurClothing.services.TransactionRewardService;
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
public class TransactionRewardServiceImpl implements TransactionRewardService {
    private final TransactionRewardRepository transactionRewardRepository;
    private final CustomerService customerService;
    private final RewardService rewardService;
    private final ValidationUtils validationUtils;
    private final TransactionRewardMapperImpl transactionRewardMapperImpl;
    private final UsersServiceImpl usersServiceImpl;


    @Override
    public TransactionReward create(CreateTransactionRewardRequest request) {
        validationUtils.validate(request);
        Customer byUser = customerService.getByUser();
        CreateRewardRequest createRewardRequest = CreateRewardRequest.builder().rewardName(request.getRewardName()).build();
        Reward reward = rewardService.create(createRewardRequest);

        if(reward.getRequiredPoints() > byUser.getPoints())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }

        TransactionReward transactionReward = new TransactionReward();
        transactionReward.setReward(reward);
        transactionReward.setCustomer(byUser);
        transactionReward.setTransactionDate(LocalDateTime.now());

        UpdatePointsCustomerRequest build = UpdatePointsCustomerRequest.builder().points(byUser.getPoints() - reward.getRequiredPoints()).build();
        customerService.updatePoints(build);
        return transactionRewardRepository.saveAndFlush(transactionReward);
    }

    @Override
    public TransactionReward update(UpdateTransactionRewardRequest request) {
        validationUtils.validate(request);
        TransactionReward byId = getById(request.getId());
        if(!byId.getCustomer().getUsersCustomer().getUserId().equals(usersServiceImpl.getByContext().getUserId()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
        }
        byId.setReward(rewardService.create(CreateRewardRequest.builder().rewardName(request.getRewardName()).build()));
        return transactionRewardRepository.saveAndFlush(byId);
    }

    @Override
    public List<TransactionReward> getAll() {
        return transactionRewardRepository.findAll();
    }

    @Override
    public TransactionReward getById(String id) {
        if(Objects.isNull(id))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return transactionRewardRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public TransactionRewardResponse createResponse(CreateTransactionRewardRequest request) {
        TransactionReward transactionReward = create(request);
        return transactionRewardMapperImpl.map(transactionReward);
    }

    @Override
    public TransactionRewardResponse updateResponse(UpdateTransactionRewardRequest request) {
        TransactionReward update = update(request);
        return transactionRewardMapperImpl.map(update);
    }

    @Override
    public List<TransactionRewardResponse> getAllResponses() {
        return getAll().stream().map(
                transactionRewardMapperImpl::map
        ).toList();
    }

    @Override
    public TransactionRewardResponse getByIdResponse(String id) {
        TransactionReward byId = getById(id);
        return transactionRewardMapperImpl.map(byId);
    }
}
