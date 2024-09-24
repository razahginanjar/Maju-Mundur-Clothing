package com.razahdev.MajuMundurClothing.controllers;


import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.requests.CreateMerchantRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateMerchantRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CommonResponse;
import com.razahdev.MajuMundurClothing.dto.responses.MerchantResponse;
import com.razahdev.MajuMundurClothing.services.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.MERCHANT)
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Merchant")
public class MerchantController {
    private final MerchantService merchantService;

    @Operation(
            description = "Get all merchant (ADMIN PRIVILEGE)",
            summary = "Get all merchant "
    )
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<List<MerchantResponse>>> getAllmerchant() {

        List<MerchantResponse> allResponses = merchantService.getAllResponses();
        CommonResponse<List<MerchantResponse>> response = CommonResponse.<List<MerchantResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(allResponses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Get specific merchant (ADMIN PRIVILEGE)",
            summary = "Get specific merchant "
    )
    
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping(
            path = ApiUrl.PATH_VAR_ID,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<MerchantResponse>> getMerchantById(@PathVariable String id) {
        MerchantResponse merchantById = merchantService.getByIdResponse(id);
        CommonResponse<MerchantResponse> response = CommonResponse.<MerchantResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(merchantById)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Update merchant information (ADMIN PRIVILEGE)",
            summary = "Update merchant information"
    )
    
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<MerchantResponse>> updateMerchant(@Valid @RequestBody UpdateMerchantRequest request) {
        MerchantResponse update = merchantService.updateResponse(request);
        CommonResponse<MerchantResponse> response = CommonResponse.<MerchantResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(update)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            description = "Delete specific merchant (ADMIN PRIVILEGE)",
            summary = "Delete specific merchant"
    )
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @DeleteMapping(
            path =ApiUrl.DELETE_ACCOUNT + ApiUrl.PATH_VAR_ID,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<String>> deleteMerchantById(@PathVariable String id) {
        merchantService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Removed Merchant with id: " + id)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}