package com.razahdev.MajuMundurClothing.mapper;

import com.razahdev.MajuMundurClothing.dto.responses.ClothResponse;
import com.razahdev.MajuMundurClothing.entities.Cloth;

public interface ClothMapper {
    ClothResponse map(Cloth cloth);
}
