package com.razahdev.MajuMundurClothing.controllers;

import com.razahdev.MajuMundurClothing.constants.ApiUrl;
import com.razahdev.MajuMundurClothing.dto.requests.CreateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.requests.UpdateTransactionRequest;
import com.razahdev.MajuMundurClothing.dto.responses.CommonResponse;
import com.razahdev.MajuMundurClothing.dto.responses.TransactionResponse;
import com.razahdev.MajuMundurClothing.services.impl.TransactionServiceImpl;
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
public class TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;

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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
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

    @GetMapping(
          path = "/cloth"+ApiUrl.PATH_VAR_ID,
          produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<TransactionResponse>>> getAllTransactionByCloth(
            @PathVariable("id")String idCloth
    )
    {
        List<TransactionResponse> list = transactionServiceImpl.getByClothResponses(idCloth);
        return ResponseEntity.status(HttpStatus.OK).body(
                CommonResponse.<List<TransactionResponse>>builder()
                        .data(list)
                        .statusCode(HttpStatus.OK.value())
                        .message("Succcess get all transaction customer")
                        .build()
        );
    }

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
                        .message("Succcess get all transaction customer")
                        .build()
        );
    }

}
