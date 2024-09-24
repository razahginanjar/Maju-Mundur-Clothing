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
    private String employeeId;
    private String employeeName;
    private String department;
}
