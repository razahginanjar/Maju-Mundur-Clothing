package com.razahdev.MajuMundurClothing.services;

import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;

public interface UsersRoleService {
    UsersRoles getOrSave(ConstantRole role);
}
