package com.coconet.server.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String name;
    private String authResult;
    private String state;

    // JWT 관련
    //private String AccessToken;
    //private String RefreshToken;

}
