package com.razahdev.MajuMundurClothing.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    @NotNull
    @NotBlank
    private String billId;
    @NotNull
    @NotBlank
    private String idCloth;
}
