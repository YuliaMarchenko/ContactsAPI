package com.telran.ilcarro.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class LoginDto {
    String email;
    String password;
}
