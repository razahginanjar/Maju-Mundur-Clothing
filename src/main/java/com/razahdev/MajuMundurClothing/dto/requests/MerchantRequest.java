package com.razahdev.MajuMundurClothing.dto.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantRequest {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
}
