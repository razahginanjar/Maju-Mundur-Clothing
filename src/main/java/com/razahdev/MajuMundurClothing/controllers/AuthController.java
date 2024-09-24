package com.razahdev.MajuMundurClothing.controllers;



import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.requests.LoginRequest;
import com.razahdev.MajuMundurClothing.dto.requests.RegisterRequest;
import com.razahdev.MajuMundurClothing.services.AuthService;
import com.razahdev.MajuMundurClothing.dto.responses.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.AUTH_API)
@Tag(name = "Authentication")
public class AuthController {
    private final AuthService authService;

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @Operation(
            description = "Register new merchant (ADMIN PRIVILEGE)",
            summary = "Register new merchant"
    )
    @PostMapping(
            path = ApiUrl.PATH_REGISTER_MERCHANT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> registerMerchant(@RequestBody RegisterRequest request) {
        RegisterResponse register = authService.registerMerchant(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            description = "Register new customer",
            summary = "Register new customer"
    )
    @PostMapping(
            path = ApiUrl.PATH_REGISTER_CUSTOMER,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> registerCustomer(@RequestBody RegisterRequest request) {
        RegisterResponse register = authService.registerCustomer(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Operation(
            description = "Login",
            summary = "Login"
    )
    @PostMapping(
            path = ApiUrl.PATH_LOGIN,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<?>> login(@RequestBody LoginRequest request) {
        LoginResponse login = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase() + ". Login success")
                .data(login)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
