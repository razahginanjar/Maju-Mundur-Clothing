package com.razahdev.MajuMundurClothing.dto.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTClaims {
    private String idUser;
    private List<String> roles;
}
