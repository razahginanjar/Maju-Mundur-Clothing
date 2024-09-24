package com.razahdev.MajuMundurClothing.mapper;

import com.razahdev.MajuMundurClothing.dto.responses.MerchantResponse;
import com.razahdev.MajuMundurClothing.entities.Merchant;

public interface MerchantMapper {
    MerchantResponse map(Merchant merchant);
}
