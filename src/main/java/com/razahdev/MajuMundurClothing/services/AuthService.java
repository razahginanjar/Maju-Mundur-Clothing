package com.razahdev.MajuMundurClothing.services;


import com.razahdev.MajuMundurClothing.dto.requests.LoginRequest;
import com.razahdev.MajuMundurClothing.dto.requests.RegisterRequest;
import com.razahdev.MajuMundurClothing.dto.responses.LoginResponse;
import com.razahdev.MajuMundurClothing.dto.responses.RegisterResponse;

public interface AuthService {
    LoginResponse login(LoginRequest login);

    RegisterResponse registerMerchant(RegisterRequest request);

    RegisterResponse registerCustomer(RegisterRequest request);
}
