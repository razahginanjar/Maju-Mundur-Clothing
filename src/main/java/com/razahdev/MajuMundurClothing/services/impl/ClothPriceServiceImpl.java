package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.entities.ClothPrice;
import com.razahdev.MajuMundurClothing.repository.ClothPriceRepository;
import com.razahdev.MajuMundurClothing.services.ClothPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClothPriceServiceImpl implements ClothPriceService {
    private final ClothPriceRepository clothPriceRepository;

    @Override
    public ClothPrice create(Long price) {
        return clothPriceRepository.findClothPriceByPrice(price).orElseGet(
                () -> clothPriceRepository.saveAndFlush(
                        ClothPrice.builder().price(price).build()
                )
        );
    }
}
