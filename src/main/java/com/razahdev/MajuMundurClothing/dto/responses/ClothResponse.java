package com.razahdev.MajuMundurClothing.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClothResponse {

    private String clothId;

    private String name;

    private Long price;

    private String productPriceId;
}
