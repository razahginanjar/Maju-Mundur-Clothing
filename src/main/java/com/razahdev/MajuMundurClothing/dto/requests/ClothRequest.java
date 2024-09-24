package com.razahdev.MajuMundurClothing.dto.requests;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClothRequest {
    private String id;
    private String name;
    private Long price;
}
