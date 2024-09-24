package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.dto.requests.CreateCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdatePointsCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CustomerResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Users;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;
import com.razahdev.MajuMundurClothing.mapper.impl.CustomerMapperImpl;
import com.razahdev.MajuMundurClothing.repository.CustomerRepository;
import com.razahdev.MajuMundurClothing.services.CustomerService;
import com.razahdev.MajuMundurClothing.services.UserService;
import com.razahdev.MajuMundurClothing.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final ValidationUtils validationUtils;
    private final CustomerMapperImpl customerMapperImpl;

    @Override
    public Customer createCustomer(CreateCustomerRequest createCustomerRequest, Users users) {
        validationUtils.validate(createCustomerRequest);
        Customer customer = new Customer();
        customer.setName(createCustomerRequest.getName());
        customer.setEmail(createCustomerRequest.getEmail());
        customer.setPoints(0);
        customer.setUsersCustomer(users);
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer update(UpdateCustomerRequest request) {
        validationUtils.validate(request);
        Users byContext = userService.getByContext();
        Customer byId = getById(request.getId());
        List<ConstantRole> list = byContext.getUsersRoles().stream().map(
                UsersRoles::getRole
        ).toList();
        if(!list.contains(ConstantRole.ROLE_ADMINISTRATOR) && !list.contains(ConstantRole.ROLE_MERCHANT))
        {
            if(!byId.getUsersCustomer().getUserId().equals(byContext.getUserId()))
            {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
            }
        }
        byId.setName(request.getName());
        byId.setEmail(request.getEmail());
        return customerRepository.saveAndFlush(byId);
    }

    @Override
    public Customer getById(String id) {
        if(Objects.isNull(id))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return customerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updatePoints(UpdatePointsCustomerRequest request) {
        validationUtils.validate(request);
        Customer byId = getById(request.getId());
        byId.setPoints(request.getPoints());
        return customerRepository.saveAndFlush(byId);
    }

    @Override
    public Customer getByUser() {
        Users byContext = userService.getByContext();
        return customerRepository.findCustomerByUsersCustomer(byContext).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public void deleteById(String id) {
        Customer byId = getById(id);
        Users usersCustomer = byId.getUsersCustomer();
        customerRepository.delete(byId);
        userService.deleteUserByID(usersCustomer.getUserId());
    }

    @Override
    public CustomerResponse createCustomerResponse(CreateCustomerRequest createCustomerRequest, Users users) {
        Customer customer = createCustomer(createCustomerRequest, users);
        return customerMapperImpl.map(customer);
    }

    @Override
    public CustomerResponse updateResponse(UpdateCustomerRequest request) {
        Customer update = update(request);
        return customerMapperImpl.map(update);
    }

    @Override
    public CustomerResponse getByIdResponse(String id) {
        Customer byId = getById(id);
        return customerMapperImpl.map(byId);
    }

    @Override
    public List<CustomerResponse> getAllResponses() {
        return getAll().stream().map(
                customerMapperImpl::map
        ).toList();
    }

    @Override
    public CustomerResponse getByUserResponse() {
        return customerMapperImpl.map(getByUser());
    }
}
