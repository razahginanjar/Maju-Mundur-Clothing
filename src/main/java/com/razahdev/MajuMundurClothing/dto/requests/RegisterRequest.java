package com.razahdev.MajuMundurClothing.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
}
