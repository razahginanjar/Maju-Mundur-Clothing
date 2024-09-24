package com.razahdev.MajuMundurClothing.mapper.impl;

import com.razahdev.MajuMundurClothing.dto.responses.MerchantResponse;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.mapper.MerchantMapper;
import org.springframework.stereotype.Service;

@Service
public class MerchantMapperImpl implements MerchantMapper {
    @Override
    public MerchantResponse map(Merchant merchant) {
        return MerchantResponse.builder()
                .merchantId(merchant.getId())
                .merchantEmail(merchant.getEmail())
                .merchantName(merchant.getMerchantName())
                .merchantPhone(merchant.getPhoneNumber())
                .build();
    }
}
