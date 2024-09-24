package com.razahdev.MajuMundurClothing.mapper;

import com.razahdev.MajuMundurClothing.dto.responses.RewardResponse;
import com.razahdev.MajuMundurClothing.entities.Reward;

public interface RewardMapper {
    RewardResponse map(Reward reward);
}
