package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.MerchantRequest;
import com.razahdev.MajuMundurClothing.dto.responses.MerchantResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.entities.Users;

import java.util.List;

public interface MerchantService {
    Merchant createMerchant(MerchantRequest request, Users merchantUser);
    Merchant update(MerchantRequest request);
    List<Merchant> getAll();
    Merchant getById(String id);
    Merchant getByUser();
    void deleteById(String id);
    MerchantResponse createMerchantResponse(MerchantRequest request, Users merchantUser);
    MerchantResponse updateResponse(MerchantRequest request);
    List<MerchantResponse> getAllResponses();
    MerchantResponse getByIdResponse(String id);
}
