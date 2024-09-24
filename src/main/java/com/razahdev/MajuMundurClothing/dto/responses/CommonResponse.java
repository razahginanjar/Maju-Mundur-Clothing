package com.razahdev.MajuMundurClothing.dto.responses;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private int statusCode;
    private String message;
    private T data;

}