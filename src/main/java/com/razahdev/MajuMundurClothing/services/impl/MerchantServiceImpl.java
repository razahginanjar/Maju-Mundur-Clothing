package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.dto.requests.MerchantRequest;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.entities.Users;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;
import com.razahdev.MajuMundurClothing.repository.MerchantRepository;
import com.razahdev.MajuMundurClothing.services.MerchantService;
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
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final UserService userService;
    private final ValidationUtils validationUtils;

    @Override
    public Merchant update(MerchantRequest request) {
        validationUtils.validate(request);
        Merchant byId = getById(request.getId());
        Users byContext = userService.getByContext();
        List<ConstantRole> list = byContext.getUsersRoles().stream().map(
                UsersRoles::getRole
        ).toList();
        if(!list.contains(ConstantRole.ROLE_ADMINISTRATOR))
        {
            if(!byId.getUsersMerchant().getUserId().equals(byContext.getUserId()))
            {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase());
            }
        }
        byId.setEmail(request.getEmail());
        byId.setMerchantName(request.getName());
        byId.setPhoneNumber(request.getPhoneNumber());

        return merchantRepository.saveAndFlush(byId);
    }

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant getById(String id) {
        if(Objects.isNull(id))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
        return merchantRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public Merchant getByUser() {
        Users byContext = userService.getByContext();
        return merchantRepository.findMerchantByUsersMerchant(byContext).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        HttpStatus.NOT_FOUND.getReasonPhrase())
        );
    }

    @Override
    public void deleteById(String id) {
        Merchant byId = getById(id);
        merchantRepository.delete(byId);
    }
}
