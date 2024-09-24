package com.razahdev.MajuMundurClothing.controllers;

import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.requests.CreateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CommonResponse;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionResponse;
import com.razahdev.MajuMundurClothing.services.impl.TransactionServiceImpl;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.TRANSACTION_API)
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;
    
    @Operation(
            description = "create transaction",
            summary = "create transaction"
    )
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<TransactionResponse>> create(@RequestBody CreateTransactionRequest request)
    {
        TransactionResponse transactionResponse = transactionServiceImpl.createResponse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.<TransactionResponse>builder()
                        .data(transactionResponse)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("success")
                        .build()
        );
    }

    @Operation(
            description = "Update transaction information",
            summary = "Update transaction information"
    )
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<TransactionResponse>> update(@RequestBody UpdateTransactionRequest request)
    {
        TransactionResponse transactionResponse = transactionServiceImpl.updateResponse(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<TransactionResponse>builder()
                        .data(transactionResponse)
                        .statusCode(HttpStatus.OK.value())
                        .message("success")
                        .build()
        );
    }

    @Operation(
            description = "get transaction information (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "get transaction information"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @GetMapping(
            path = ApiUrl.PATH_VAR_ID,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<TransactionResponse>> get(@PathVariable(name = "id") String idBill)
    {
        TransactionResponse transactionResponse = transactionServiceImpl.getByIdResponse(idBill);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<TransactionResponse>builder()
                        .data(transactionResponse)
                        .statusCode(HttpStatus.OK.value())
                        .message("success")
                        .build()
        );
    }

    @Operation(
            description = "get all transaction information (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "get all transaction information"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<TransactionResponse>>>getList(
            @RequestParam(name = "cloth_name", required = false) String clothName)
    {
        List<TransactionResponse> list;
        if(clothName == null)
        {
            list = transactionServiceImpl.getAllResponses();
        }else{
            list = transactionServiceImpl.getByClothResponses(clothName);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<List<TransactionResponse>>builder()
                        .data(list)
                        .statusCode(HttpStatus.OK.value())
                        .message("Success get transactions")
                        .build()
        );
    }

}
