package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CreateMerchantRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateMerchantRequest;
import com.razahdev.MajuMundurClothing.dto.responses.MerchantResponse;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.entities.Users;

import java.util.List;

public interface MerchantService {
    Merchant createMerchant(CreateMerchantRequest request, Users merchantUser);
    Merchant update(UpdateMerchantRequest request);
    List<Merchant> getAll();
    Merchant getById(String id);
    Merchant getByUser();
    void deleteById(String id);
    MerchantResponse createMerchantResponse(CreateMerchantRequest request, Users merchantUser);
    MerchantResponse updateResponse(UpdateMerchantRequest request);
    List<MerchantResponse> getAllResponses();
    MerchantResponse getByIdResponse(String id);
}
