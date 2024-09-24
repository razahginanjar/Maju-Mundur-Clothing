package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.entities.Users;
import com.razahdev.MajuMundurClothing.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UserService {

    @Override
    public Users getUserByID(String idUser) {
        return null;
    }

    @Override
    public Users getByContext() {
        return null;
    }

    @Override
    public void deleteUserByID(String idUser) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
