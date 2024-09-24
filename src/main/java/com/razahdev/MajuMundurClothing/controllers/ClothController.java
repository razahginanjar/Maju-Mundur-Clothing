package com.razahdev.MajuMundurClothing.controllers;


import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.requests.CreateClothRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateClothRequest;
import com.razahdev.MajuMundurClothing.dto.responses.ClothResponse;
import com.razahdev.MajuMundurClothing.dto.responses.CommonResponse;
import com.razahdev.MajuMundurClothing.services.impl.ClothServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = ApiUrl.PRODUCT_API)
@Tag(name = "Cloth")
@SecurityRequirement(name = "bearerAuth")
public class ClothController {

    private final ClothServiceImpl clothService;
    @Operation(
            description = "Create new cloth (ADMIN, MERCHANT PRIVILEGE)",
            summary = "Create new cloth"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<ClothResponse>> create(@RequestBody CreateClothRequest request)
    {
        ClothResponse ClothResponse = clothService.createResponse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.<ClothResponse>builder()
                        .data(ClothResponse)
                        .message("success")
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }
    @Operation(
            description = "get all cloth",
            summary = "get all cloth"
    )
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<ClothResponse>>> getList()
    {
        List<ClothResponse> list = clothService.getAllResponses();
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<List<ClothResponse>>builder()
                        .data(list)
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .build()
        );
    }
    @Operation(
            description = "Update cloth (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "Update cloth"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<ClothResponse>> update(@RequestBody UpdateClothRequest request)
    {
        ClothResponse update = clothService.updateResponse(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<ClothResponse>builder()
                        .data(update)
                        .message("success")
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
    @Operation(
            description = "delete cloth (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "delete cloth"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @DeleteMapping(
            path = "{id_product}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable(name = "id_product") String idProduct)
    {
        clothService.deleteById(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<String>builder()
                        .data("OK")
                        .message("success")
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


}
