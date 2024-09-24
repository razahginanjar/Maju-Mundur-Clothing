package com.razahdev.MajuMundurClothing.controllers;

import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.services.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerServiceImpl customerServiceImpl;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<GenericResponse<CustomerResponse>>  getCustomerById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        GenericResponse.<CustomerResponse>builder()
                                .data(customerServiceImpl.getById(id))
                                .message("Successfully get Customer")
                                .status(HttpStatus.ACCEPTED.value())
                                .build()
                );
    }

    @GetMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<GenericResponse<CustomerResponse>> seeYourOwnAccount(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        GenericResponse.<CustomerResponse>builder()
                                .data(customerServiceImpl.getById(id))
                                .message("Successfully get Customer")
                                .status(HttpStatus.ACCEPTED.value())
                                .build()
                );
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping
    public ResponseEntity<GenericResponse<List<CustomerResponse>>> getAllCustomer() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        GenericResponse.<List<CustomerResponse>>builder()
                                .status(HttpStatus.ACCEPTED.value())
                                .message("Successfully get all Customer")
                                .data(customerServiceImpl.getAll())
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<GenericResponse<CustomerResponse>>  updateCustomer(@RequestBody UpdateCustomerRequest customer) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(
                        GenericResponse.<CustomerResponse>builder()
                                .data(customerServiceImpl.update(customer))
                                .message("Successfully updated product")
                                .status(HttpStatus.ACCEPTED.value())
                                .build()
                );
    }

    @DeleteMapping(path = ApiUrl.PATH_VAR_ID)
    public ResponseEntity<GenericResponse<String>>  deleteCustomerById(@PathVariable String id) {
        customerServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        GenericResponse.<String>builder()
                                .data(ConstantMessage.DELETESUCCESS + id)
                                .status(HttpStatus.OK.value())
                                .message("Successfully deleted customer")
                                .build()
                );
    }
}
