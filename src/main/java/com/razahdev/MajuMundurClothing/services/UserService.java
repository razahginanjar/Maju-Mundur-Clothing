package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users getUserByID(String idUser);
    Users getByContext();
    void deleteUserByID(String idUser);
}
