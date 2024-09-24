package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.dto.requests.ClothRequest;
import com.razahdev.MajuMundurClothing.dto.responses.ClothResponse;
import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.entities.ClothPrice;
import com.razahdev.MajuMundurClothing.mapper.ClothMapper;
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
    private final ClothMapper clothMapper;

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

    @Override
    public Cloth getByName(String name) {
        if(Objects.isNull(name))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return clothRepository.findClothByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public ClothResponse createResponse(ClothRequest request) {
        validationUtils.validate(request);
        Cloth cloth = create(request);
        return clothMapper.map(cloth);
    }

    @Override
    public ClothResponse updateResponse(ClothRequest request) {
        validationUtils.validate(request);
        Cloth update = update(request);
        return clothMapper.map(update);
    }

    @Override
    public List<ClothResponse> getAllResponses() {
        return getAll().stream().map(
                clothMapper::map
        ).toList();
    }

    @Override
    public ClothResponse getByIdResponses(String id) {
        Cloth byId = getById(id);
        return clothMapper.map(byId);
    }
}
