package com.razahdev.MajuMundurClothing.services.impl;

import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.dto.requests.CreateMerchantRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateMerchantRequest;
import com.razahdev.MajuMundurClothing.dto.responses.MerchantResponse;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.entities.Users;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;
import com.razahdev.MajuMundurClothing.mapper.impl.MerchantMapperImpl;
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
    private final MerchantMapperImpl merchantMapperImpl;

    @Override
    public Merchant createMerchant(CreateMerchantRequest request, Users merchantUser) {
        validationUtils.validate(request);
        Merchant merchant = new Merchant();
        merchant.setMerchantName(request.getName());
        merchant.setUsersMerchant(merchantUser);
        merchant.setEmail(request.getEmail());
        merchant.setPhoneNumber(request.getPhoneNumber());
        return merchantRepository.saveAndFlush(merchant);
    }

    @Override
    public Merchant update(UpdateMerchantRequest request) {
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
        userService.deleteUserByID(byId.getUsersMerchant().getUserId());
    }

    @Override
    public MerchantResponse createMerchantResponse(CreateMerchantRequest request, Users merchantUser) {
        Merchant merchant = createMerchant(request, merchantUser);
        return merchantMapperImpl.map(merchant);
    }

    @Override
    public MerchantResponse updateResponse(UpdateMerchantRequest request) {
        Merchant update = update(request);
        return merchantMapperImpl.map(update);
    }

    @Override
    public List<MerchantResponse> getAllResponses() {
        return getAll().stream().map(
                merchantMapperImpl::map
        ).toList();
    }

    @Override
    public MerchantResponse getByIdResponse(String id) {
        Merchant byId = getById(id);
        return merchantMapperImpl.map(byId);
    }
}
