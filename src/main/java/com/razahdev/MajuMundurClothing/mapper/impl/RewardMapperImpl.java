package com.razahdev.MajuMundurClothing.mapper.impl;

import com.razahdev.MajuMundurClothing.dto.responses.RewardResponse;
import com.razahdev.MajuMundurClothing.entities.Reward;
import com.razahdev.MajuMundurClothing.mapper.RewardMapper;
import org.springframework.stereotype.Service;

@Service
public class RewardMapperImpl implements RewardMapper {
    @Override
    public RewardResponse map(Reward reward) {
        return RewardResponse.builder()
                .id(reward.getId())
                .name(reward.getRewardName())
                .points(reward.getRequiredPoints())
                .build();
    }
}
