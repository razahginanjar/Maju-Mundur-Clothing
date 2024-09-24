package com.razahdev.MajuMundurClothing.controllers;

import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.requests.CreateTransactionRewardRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateTransactionRewardRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CommonResponse;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionRewardResponse;
import com.razahdev.MajuMundurClothing.services.impl.TransactionRewardServiceImpl;
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
@RequestMapping(path = ApiUrl.TRANSACTION_REWARD_API)
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Transaction Reward")
public class TransactionRewardController {

    private final TransactionRewardServiceImpl transactionRewardServiceImpl;

    @Operation(
            description = "create transaction reward",
            summary = "create transaction reward"
    )
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<TransactionRewardResponse>> create(@RequestBody CreateTransactionRewardRequest request)
    {
        TransactionRewardResponse TransactionRewardResponse = transactionRewardServiceImpl.createResponse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommonResponse.<TransactionRewardResponse>builder()
                        .data(TransactionRewardResponse)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("success")
                        .build()
        );
    }

    @Operation(
            description = "update transaction reward",
            summary = "update transaction reward"
    )
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<TransactionRewardResponse>> update(@RequestBody UpdateTransactionRewardRequest request)
    {
        TransactionRewardResponse TransactionRewardResponse = transactionRewardServiceImpl.updateResponse(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<TransactionRewardResponse>builder()
                        .data(TransactionRewardResponse)
                        .statusCode(HttpStatus.OK.value())
                        .message("success")
                        .build()
        );
    }

    @Operation(
            description = "get specific transaction reward (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "get specific transaction reward"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @GetMapping(
            path = ApiUrl.PATH_VAR_ID,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<TransactionRewardResponse>> get(@PathVariable(name = "id") String idBill)
    {
        TransactionRewardResponse TransactionRewardResponse = transactionRewardServiceImpl.getByIdResponse(idBill);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<TransactionRewardResponse>builder()
                        .data(TransactionRewardResponse)
                        .statusCode(HttpStatus.OK.value())
                        .message("success")
                        .build()
        );
    }

    @Operation(
            description = "get all transaction reward (ADMINISTRATOR, MERCHANT PRIVILEGE)",
            summary = "get all transaction reward"
    )
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MERCHANT')")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<TransactionRewardResponse>>>getList()
    {
        List<TransactionRewardResponse> list = transactionRewardServiceImpl.getAllResponses();
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<List<TransactionRewardResponse>>builder()
                        .data(list)
                        .statusCode(HttpStatus.OK.value())
                        .message("Succcess get all transaction customer")
                        .build()
        );
    }
    
}
