package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CreateRewardRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateRewardRequest;
import com.razahdev.MajuMundurClothing.dto.responses.RewardResponse;
import com.razahdev.MajuMundurClothing.entities.Reward;

import java.util.List;

public interface RewardService {
    Reward create(CreateRewardRequest request);
    Reward update(UpdateRewardRequest request);
    List<Reward> getAll();
    Reward getById(String id);
    void deleteById(String id);

    RewardResponse createResponse(CreateRewardRequest request);
    RewardResponse updateResponse(UpdateRewardRequest request);
    List<RewardResponse> getAllResponses();
    RewardResponse getByIdResponse(String id);
}
