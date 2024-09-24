package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CreateClothRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateClothRequest;
import com.razahdev.MajuMundurClothing.dto.responses.ClothResponse;
import com.razahdev.MajuMundurClothing.entities.Cloth;

import java.util.List;

public interface ClothService {
    Cloth create(CreateClothRequest request);
    Cloth update(UpdateClothRequest request);
    List<Cloth> getAll();
    Cloth getById(String id);
    void deleteById(String id);
    Cloth getByName(String name);

    ClothResponse createResponse(CreateClothRequest request);
    ClothResponse updateResponse(UpdateClothRequest request);
    List<ClothResponse> getAllResponses();
    ClothResponse getByIdResponses(String id);
}
