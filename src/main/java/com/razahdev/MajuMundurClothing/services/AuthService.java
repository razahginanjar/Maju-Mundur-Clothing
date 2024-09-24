package com.razahdev.MajuMundurClothing.services;


import com.razahdev.MajuMundurClothing.dto.requests.LoginRequest;
import com.razahdev.MajuMundurClothing.dto.requests.RegisterRequest;
import com.razahdev.MajuMundurClothing.dto.responses.LoginResponse;
import com.razahdev.MajuMundurClothing.dto.responses.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest register);
    LoginResponse login(LoginRequest login);

    RegisterResponse registerEmployee(RegisterRequest request);

    RegisterResponse registerCustomer(RegisterRequest request);
}
