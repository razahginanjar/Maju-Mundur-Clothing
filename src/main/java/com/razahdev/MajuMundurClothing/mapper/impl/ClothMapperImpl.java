package com.razahdev.MajuMundurClothing.mapper.impl;

import com.razahdev.MajuMundurClothing.dto.responses.ClothResponse;
import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.mapper.ClothMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ClothMapperImpl implements ClothMapper {
    @Override
    public ClothResponse map(Cloth cloth) {
        return ClothResponse.builder()
                .clothId(cloth.getId())
                .name(cloth.getName())
                .price(cloth.getClothPrice().getPrice())
                .build();
    }
}
