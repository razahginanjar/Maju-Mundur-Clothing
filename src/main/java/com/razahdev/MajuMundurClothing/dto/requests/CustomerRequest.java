package com.razahdev.MajuMundurClothing.dto.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String id;
    private String name;
    private Integer points;
    private String email;
}
