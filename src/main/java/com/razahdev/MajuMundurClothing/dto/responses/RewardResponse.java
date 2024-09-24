package com.razahdev.MajuMundurClothing.dto.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RewardResponse {
    private String id;
    private String name;
    private Integer points;
}
