package com.razahdev.MajuMundurClothing.controllers;


import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.responses.ClothResponse;
import com.razahdev.MajuMundurClothing.services.impl.ClothServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = ApiUrl.PRODUCT_API)
public class ClothController {

    private final ClothServiceImpl productServicesImpl;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<ClothResponse>> create(@RequestBody ProductRequest request)
    {
        ClothResponse ClothResponse = productServicesImpl.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                GenericResponse.<ClothResponse>builder()
                        .data(ClothResponse)
                        .message("success")
                        .status(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<List<ClothResponse>>> getList()
    {
        Page<ClothResponse> list = productServicesImpl.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                GenericResponse.<List<ClothResponse>>builder()
                        .data(list.getContent())
                        .responsePaging(
                                ResponsePaging.builder()
                                        .count(list.getNumberOfElements())
                                        .page(list.getNumber())
                                        .size(list.getSize())
                                        .totalPage(list.getTotalPages())
                                        .build()
                        )
                        .status(HttpStatus.OK.value())
                        .message("Success")
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<ClothResponse>> update(@RequestBody UpdateProductRequest request)
    {
        ClothResponse update = productServicesImpl.update(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                GenericResponse.<ClothResponse>builder()
                        .data(update)
                        .message("success")
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping(
            path = "{id_product}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponse<String>> delete(@PathVariable(name = "id_product") String idProduct)
    {
        productServicesImpl.delete(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(
                GenericResponse.<String>builder()
                        .data("OK")
                        .message("success")
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }


}
