package com.razahdev.MajuMundurClothing.services.impl;


import com.razahdev.MajuMundurClothing.constants.ConstantRole;
import com.razahdev.MajuMundurClothing.dto.requests.CustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.LoginRequest;
import com.razahdev.MajuMundurClothing.dto.requests.MerchantRequest;
import com.razahdev.MajuMundurClothing.dto.requests.RegisterRequest;
import com.razahdev.MajuMundurClothing.dto.responses.LoginResponse;
import com.razahdev.MajuMundurClothing.dto.responses.RegisterResponse;
import com.razahdev.MajuMundurClothing.entities.Customer;
import com.razahdev.MajuMundurClothing.entities.Merchant;
import com.razahdev.MajuMundurClothing.entities.Users;
import com.razahdev.MajuMundurClothing.entities.UsersRoles;
import com.razahdev.MajuMundurClothing.repository.UsersRepository;
import com.razahdev.MajuMundurClothing.repository.UsersRoleRepository;
import com.razahdev.MajuMundurClothing.services.AuthService;
import com.razahdev.MajuMundurClothing.services.JwtService;
import com.razahdev.MajuMundurClothing.services.MerchantService;
import com.razahdev.MajuMundurClothing.services.UsersRoleService;
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

        MerchantRequest merchantRequest = MerchantRequest.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        Merchant merchant1 = merchantService.createMerchant(merchantRequest, user);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .merchant(employeeResponse)
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

        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(request.getName())
                .points(0)
                .email(request.getEmail())
                .build();
        Customer customer1 = customerServiceImpl.createCustomer(customerRequest, user);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .customer(employeeResponse)
                .build();
    }
}
