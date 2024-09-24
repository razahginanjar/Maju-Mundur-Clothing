package com.razahdev.MajuMundurClothing.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRewardRequest {
    @NotBlank
    @NotNull
    private String rewardName;
    @NotBlank
    @NotNull
    private Integer points;
}
