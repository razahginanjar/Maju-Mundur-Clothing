package com.razahdev.MajuMundurClothing.services;


import com.razahdev.MajuMundurClothing.dto.responses.JWTClaims;
import com.razahdev.MajuMundurClothing.entities.Users;

public interface JwtService {
    String generateToken(Users userAccount);
    Boolean verifyToken(String token);
    JWTClaims claimToken(String token);
}
