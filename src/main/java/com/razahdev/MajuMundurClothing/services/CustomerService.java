package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CustomerRequest;
import com.razahdev.MajuMundurClothing.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer update(CustomerRequest request);
    Customer getById(String id);
    List<Customer> getAll();
    Customer updatePoints(CustomerRequest request);
    Customer getByUser();
    void deleteById(String id);
}
