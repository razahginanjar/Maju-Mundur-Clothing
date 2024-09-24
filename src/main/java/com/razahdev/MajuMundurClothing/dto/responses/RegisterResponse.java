package com.razahdev.MajuMundurClothing.dto.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String username;
    private List<String> roles;
    private MerchantResponse merchant;
    private CustomerResponse customer;
}
