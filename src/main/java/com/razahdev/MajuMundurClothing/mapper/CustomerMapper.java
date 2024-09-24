package com.razahdev.MajuMundurClothing.mapper;

import com.razahdev.MajuMundurClothing.dto.responses.CustomerResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;

public interface CustomerMapper {
    CustomerResponse map(Customer customer);
}
