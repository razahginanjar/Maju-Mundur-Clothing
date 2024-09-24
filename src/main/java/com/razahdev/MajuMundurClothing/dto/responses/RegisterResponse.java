package com.razahdev.MajuMundurClothing.dto.responses;

import com.enigma.challengebookingroom.dto.response.EmployeeResponse;
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
    private EmployeeResponse employee;
}
