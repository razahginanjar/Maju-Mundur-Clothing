package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CustomerRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CustomerResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Users;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CustomerRequest customerRequest, Users users);
    Customer update(CustomerRequest request);
    Customer getById(String id);
    List<Customer> getAll();
    Customer updatePoints(CustomerRequest request);
    Customer getByUser();
    void deleteById(String id);

    CustomerResponse createCustomerResponse(CustomerRequest customerRequest, Users users);
    CustomerResponse updateResponse(CustomerRequest request);
    CustomerResponse getByIdResponse(String id);
    List<CustomerResponse> getAllResponses();
}
