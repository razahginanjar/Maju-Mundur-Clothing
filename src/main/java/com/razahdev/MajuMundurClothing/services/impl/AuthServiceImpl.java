package com.razahdev.MajuMundurClothing.services.impl;


import com.razahdev.MajuMundurClothing.dto.requests.LoginRequest;
import com.razahdev.MajuMundurClothing.dto.requests.RegisterRequest;
import com.razahdev.MajuMundurClothing.dto.responses.LoginResponse;
import com.razahdev.MajuMundurClothing.dto.responses.RegisterResponse;
import com.razahdev.MajuMundurClothing.entities.Users;
import com.razahdev.MajuMundurClothing.repository.UsersRepository;
import com.razahdev.MajuMundurClothing.repository.UsersRoleRepository;
import com.razahdev.MajuMundurClothing.services.AuthService;
import com.razahdev.MajuMundurClothing.services.JwtService;
import com.razahdev.MajuMundurClothing.services.MerchantService;
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

    private final UsersRoleRepository roleService;
    private final PasswordEncoder passwordEncoder;
    private final MerchantService employeeService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ValidationUtils validation;

    @Value("${challengebookingroom.superadmin.username}")
    private String superAdminUsername;
    @Value("${challengebookingroom.superadmin.password}")
    private String superAdminPassword;

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void init() {
        Optional<Users> userSuperAdmin = userRepository.findByUsername(superAdminUsername);
        if (userSuperAdmin.isPresent()) return;

        Role admin = roleService.create(RoleRequest.builder().constantRole(ConstantRole.ROLE_ADMINISTRATOR).build());
        Role ga = roleService.create(RoleRequest.builder().constantRole(ConstantRole.ROLE_GENERAL_AFFAIR).build());
        Role user = roleService.create(RoleRequest.builder().constantRole(ConstantRole.ROLE_USER).build());

        User account = User.builder()
                .username(superAdminUsername)
                .password(passwordEncoder.encode(superAdminPassword))
                .roles(List.of(admin, ga, user))
                .build();
        userRepository.save(account);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        validation.validate(request);
        Role role = roleService.getOrSave(ConstantRole.ROLE_USER);
        String hashPassword = passwordEncoder.encode(request.getPassword());

        EmployeeRequest employee = EmployeeRequest.builder()
                .employeeName(request.getEmployeeName())
                .department(request.getDepartment())
                .phoneNumber(request.getPhoneNumber())
                .corporateEmail(request.getCorporateEmail())
                .build();
        Employee response = employeeService.createAndGet(employee);

        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .employeeId(response.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .department(employee.getDepartment())
                .build();

        User user = User.builder()
                .username(request.getUsername())
                .employee(response)
                .password(hashPassword)
                .roles(List.of(role))
                .build();
        userRepository.saveAndFlush(user);

        return RegisterResponse.builder()
                .username(user.getUsername())
                .roles(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .employee(employeeResponse)
                .build();
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
    public RegisterResponse registerEmployee(RegisterRequest request) {
        return null;
    }

    @Override
    public RegisterResponse registerCustomer(RegisterRequest request) {
        return null;
    }
}
