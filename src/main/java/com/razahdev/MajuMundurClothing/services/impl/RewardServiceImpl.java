package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.dto.requests.CreateRewardRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateRewardRequest;
import com.razahdev.MajuMundurClothing.dto.responses.RewardResponse;
import com.razahdev.MajuMundurClothing.entities.Reward;
import com.razahdev.MajuMundurClothing.mapper.impl.RewardMapperImpl;
import com.razahdev.MajuMundurClothing.repository.RewardRepository;
import com.razahdev.MajuMundurClothing.services.RewardService;
import com.razahdev.MajuMundurClothing.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {
    private final RewardRepository rewardRepository;
    private final ValidationUtils validationUtils;
    private final RewardMapperImpl rewardMapperImpl;

    @Override
    public Reward create(CreateRewardRequest request) {
        validationUtils.validate(request);
        return rewardRepository.findByRewardName(request.getRewardName()).orElseGet(
                () -> rewardRepository.saveAndFlush(
                        Reward.builder()
                                .rewardName(request.getRewardName())
                                .requiredPoints(request.getPoints())
                                .build()
                )
        );
    }

    @Override
    public Reward update(UpdateRewardRequest request) {
        validationUtils.validate(request);
        Reward byId = getById(request.getId());
        byId.setRewardName(request.getRewardName());
        byId.setRequiredPoints(request.getPoints());
        return rewardRepository.saveAndFlush(byId);
    }

    @Override
    public List<Reward> getAll() {
        return rewardRepository.findAll();
    }

    @Override
    public Reward getById(String id) {
        if(Objects.isNull(id))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return rewardRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public void deleteById(String id) {
        Reward byId = getById(id);
        rewardRepository.delete(byId);
    }

    @Override
    public RewardResponse createResponse(CreateRewardRequest request) {
        Reward reward = create(request);
        return rewardMapperImpl.map(reward);
    }

    @Override
    public RewardResponse updateResponse(UpdateRewardRequest request) {
        Reward update = update(request);
        return rewardMapperImpl.map(update);
    }

    @Override
    public List<RewardResponse> getAllResponses() {
        return getAll().stream().map(
                rewardMapperImpl::map
        ).toList();
    }

    @Override
    public RewardResponse getByIdResponse(String id) {
        Reward byId = getById(id);
        return rewardMapperImpl.map(byId);
    }
}
