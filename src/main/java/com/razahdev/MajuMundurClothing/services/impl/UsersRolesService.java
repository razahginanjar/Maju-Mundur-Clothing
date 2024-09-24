package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.entities.Reward;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;
import com.razahdev.MajuMundurClothing.repository.UsersRoleRepository;
import com.razahdev.MajuMundurClothing.services.UsersRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsersRolesService implements UsersRoleService {
    private final UsersRoleRepository usersRoleRepository;

    @Override
    public UsersRoles getOrSave(ConstantRole role) {
        if(Objects.isNull(role))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return usersRoleRepository.findByRole(role).orElseGet(
                () -> usersRoleRepository.saveAndFlush(
                        UsersRoles.builder()
                                .role(role)
                                .build()
                )
        );
    }
}
