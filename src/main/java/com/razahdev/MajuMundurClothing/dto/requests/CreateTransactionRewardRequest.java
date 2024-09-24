package com.razahdev.MajuMundurClothing.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRewardRequest {
    @NotBlank
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String rewardName;
}
