package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.dto.requests.CreateCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdatePointsCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CustomerResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Users;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CreateCustomerRequest createCustomerRequest, Users users);
    Customer update(UpdateCustomerRequest request);
    Customer getById(String id);
    List<Customer> getAll();
    Customer updatePoints(UpdatePointsCustomerRequest request);
    Customer getByUser();
    void deleteById(String id);

    CustomerResponse createCustomerResponse(CreateCustomerRequest createCustomerRequest, Users users);
    CustomerResponse updateResponse(UpdateCustomerRequest request);
    CustomerResponse getByIdResponse(String id);
    List<CustomerResponse> getAllResponses();
    CustomerResponse getByUserResponse();
}
