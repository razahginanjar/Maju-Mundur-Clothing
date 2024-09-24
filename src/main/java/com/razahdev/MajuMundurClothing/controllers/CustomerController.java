package com.razahdev.MajuMundurClothing.controllers;

import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.requests.CreateCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateCustomerRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CommonResponse;
import com.razahdev.MajuMundurClothing.dto.responses.CustomerResponse;
import com.razahdev.MajuMundurClothing.services.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.CUSTOMER_API)
@Tag(name = "Customer")
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {
    private final CustomerServiceImpl customerServiceImpl;

    @Operation(
            description = "get Customer (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "get Customer"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<CustomerResponse>>  getCustomerById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        CommonResponse.<CustomerResponse>builder()
                                .data(customerServiceImpl.getByIdResponse(id))
                                .message("Successfully get Customer")
                                .statusCode(HttpStatus.ACCEPTED.value())
                                .build()
                );
    }

    @Operation(
            description = "get your own acc customer",
            summary = "get your own acc customer"
    )
    @GetMapping(
            path = "/me"
    )
    public ResponseEntity<CommonResponse<CustomerResponse>> seeYourOwnAccount() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        CommonResponse.<CustomerResponse>builder()
                                .data(customerServiceImpl.getByUserResponse())
                                .message("Successfully get Customer")
                                .statusCode(HttpStatus.ACCEPTED.value())
                                .build()
                );
    }

    @Operation(
            description = "get all (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "get all "
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @GetMapping
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        CommonResponse.<List<CustomerResponse>>builder()
                                .statusCode(HttpStatus.ACCEPTED.value())
                                .message("Successfully get all Customer")
                                .data(customerServiceImpl.getAllResponses())
                                .build()
                );
    }

    @Operation(
            description = "update customer (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "update customer"
    )
    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>>  updateCustomer(@RequestBody UpdateCustomerRequest customer) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        CommonResponse.<CustomerResponse>builder()
                                .data(customerServiceImpl.updateResponse(customer))
                                .message("Successfully updated product")
                                .statusCode(HttpStatus.ACCEPTED.value())
                                .build()
                );
    }

    @Operation(
            description = "delete cloth (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "delete cloth"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<String>>  deleteCustomerById(@PathVariable String id) {
        customerServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.<String>builder()
                                .data("Successfully deleted customer with id: " + id)
                                .statusCode(HttpStatus.OK.value())
                                .message("Successfully deleted customer")
                                .build()
                );
    }
}
