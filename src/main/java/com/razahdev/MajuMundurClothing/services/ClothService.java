package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.ClothRequest;
import com.razahdev.MajuMundurClothing.dto.requests.MerchantRequest;
import com.razahdev.MajuMundurClothing.dto.responses.ClothResponse;
import com.razahdev.MajuMundurClothing.entities.Cloth;
import com.razahdev.MajuMundurClothing.entities.Merchant;

import java.util.List;

public interface ClothService {
    Cloth create(ClothRequest request);
    Cloth update(ClothRequest request);
    List<Cloth> getAll();
    Cloth getById(String id);
    void deleteById(String id);
    Cloth getByName(String name);

    ClothResponse createResponse(ClothRequest request);
    ClothResponse updateResponse(ClothRequest request);
    List<ClothResponse> getAllResponses();
    ClothResponse getByIdResponses(String id);
}
