package com.razahdev.MajuMundurClothing.services.impl;


import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.dto.requests.*;
import com.razahdev.MajuMundurClothing.dto.responses.CustomerResponse;
import com.razahdev.MajuMundurClothing.dto.responses.LoginResponse;
import com.razahdev.MajuMundurClothing.dto.responses.MerchantResponse;
import com.razahdev.MajuMundurClothing.dto.responses.RegisterResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.entities.Users;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;
import com.razahdev.MajuMundurClothing.repository.UsersRepository;
import com.razahdev.MajuMundurClothing.services.*;
import com.razahdev.MajuMundurClothing.utils.ValidationUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsersRepository userRepository;

    private final UsersRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final MerchantService merchantService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ValidationUtils validation;
    private final RewardService rewardService;
    private final CustomerServiceImpl customerServiceImpl;

    @Value("${challengebookingroom.superadmin.username}")
    private String superAdminUsername;
    @Value("${challengebookingroom.superadmin.password}")
    private String superAdminPassword;

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void init() {
        Optional<Users> userSuperAdmin = userRepository.findByUsername(superAdminUsername);
        if (userSuperAdmin.isPresent()) return;

        UsersRoles admin = roleService.getOrSave(ConstantRole.ROLE_ADMINISTRATOR);
        UsersRoles merchant = roleService.getOrSave(ConstantRole.ROLE_MERCHANT);
        UsersRoles customer = roleService.getOrSave(ConstantRole.ROLE_CUSTOMER);

        Users account = Users.builder()
                .username(superAdminUsername)
                .password(passwordEncoder.encode(superAdminPassword))
                .usersRoles(List.of(admin, merchant, customer))
                .build();
        userRepository.save(account);
        rewardService.create(CreateRewardRequest.builder().rewardName("REWARD A").points(20).build());
        rewardService.create(CreateRewardRequest.builder().rewardName("REWARD B").points(40).build());
    }

    @Override
    public LoginResponse login(LoginRequest request) throws DataIntegrityViolationException {
        validation.validate(request);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        Authentication authenticated = authenticationManager.authenticate(authentication);
        Users account = (Users) authenticated.getPrincipal();
        String token = jwtService.generateToken(account);
        return LoginResponse.builder()
                .token(token)
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .build();
    }

    @Override
    public RegisterResponse registerMerchant(RegisterRequest request) {
        validation.validate(request);
        UsersRoles merchant = roleService.getOrSave(ConstantRole.ROLE_MERCHANT);
        UsersRoles customer = roleService.getOrSave(ConstantRole.ROLE_CUSTOMER);
        String hashPassword = passwordEncoder.encode(request.getPassword());
        Users user = Users.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .usersRoles(List.of(merchant, customer))
                .build();
        userRepository.saveAndFlush(user);

        CreateMerchantRequest createMerchantRequest = CreateMerchantRequest.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        MerchantResponse merchant1 = merchantService.createMerchantResponse(createMerchantRequest, user);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .merchant(merchant1)
                .build();
    }

    @Override
    public RegisterResponse registerCustomer(RegisterRequest request) {
        validation.validate(request);
        UsersRoles customer = roleService.getOrSave(ConstantRole.ROLE_CUSTOMER);
        String hashPassword = passwordEncoder.encode(request.getPassword());
        Users user = Users.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .usersRoles(List.of(customer))
                .build();
        userRepository.saveAndFlush(user);

        CreateCustomerRequest createCustomerRequest = CreateCustomerRequest.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
        CustomerResponse customerResponse = customerServiceImpl.createCustomerResponse(createCustomerRequest, user);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .customer(customerResponse)
                .build();
    }
}
