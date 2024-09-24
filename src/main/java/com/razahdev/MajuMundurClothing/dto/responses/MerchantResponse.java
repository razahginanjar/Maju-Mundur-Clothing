package com.razahdev.MajuMundurClothing.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantResponse {
    private String merchantId;
    private String merchantName;
    private String merchantEmail;
    private String merchantPhone;
}
