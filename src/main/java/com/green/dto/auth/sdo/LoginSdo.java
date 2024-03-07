package com.green.dto.auth.sdo;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginSdo {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String role;
}
