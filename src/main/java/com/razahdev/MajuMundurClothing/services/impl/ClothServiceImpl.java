package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.dto.requests.ClothRequest;
import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.entities.ClothPrice;
import com.razahdev.MajuMundurClothing.repository.ClothRepository;
import com.razahdev.MajuMundurClothing.services.ClothPriceService;
import com.razahdev.MajuMundurClothing.services.ClothService;
import com.razahdev.MajuMundurClothing.utils.ValidationUtils;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClothServiceImpl implements ClothService {
    private final ClothRepository clothRepository;
    private final ClothPriceService clothPriceService;
    private final ValidationUtils validationUtils;

    @Override
    public Cloth create(ClothRequest request) {
        validationUtils.validate(request);
        ClothPrice clothPrice = clothPriceService.create(request.getPrice());
        if(clothRepository.existsByName(request.getName()))
        {
            return clothRepository.findClothByName(request.getName()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            HttpStatus.NOT_FOUND.getReasonPhrase())
            );
        }
        Cloth cloth = new Cloth();
        cloth.setName(request.getName());
        cloth.setClothPrice(clothPrice);
        return clothRepository.saveAndFlush(cloth);
    }

    @Override
    public Cloth update(ClothRequest request) {
        validationUtils.validate(request);
        Cloth cloth = getById(request.getId());
        ClothPrice clothPrice = clothPriceService.create(request.getPrice());
        cloth.setClothPrice(clothPrice);
        cloth.setName(request.getName());

        return clothRepository.saveAndFlush(cloth);
    }

    @Override
    public List<Cloth> getAll() {
        return clothRepository.findAll();
    }

    @Override
    public Cloth getById(String id) {
        if(Objects.isNull(id))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return clothRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public void deleteById(String id) {
        Cloth byId = getById(id);
        clothRepository.delete(byId);
    }
}
