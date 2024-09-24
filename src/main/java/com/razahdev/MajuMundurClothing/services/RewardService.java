package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.ClothRequest;
import com.razahdev.MajuMundurClothing.dto.requests.RewardRequest;
import com.razahdev.MajuMundurClothing.dto.responses.RewardResponse;
import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.entities.Reward;

import java.util.List;

public interface RewardService {
    Reward create(RewardRequest request);
    Reward update(RewardRequest request);
    List<Reward> getAll();
    Reward getById(String id);
    void deleteById(String id);

    RewardResponse createResponse(RewardRequest request);
    RewardResponse updateResponse(RewardRequest request);
    List<RewardResponse> getAllResponses();
    RewardResponse getByIdResponse(String id);
}
