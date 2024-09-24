package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.MerchantRequest;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant update(MerchantRequest request);
    List<Merchant> getAll();
    Merchant getById(String id);
    Merchant getByUser();
    void deleteById(String id);
}
