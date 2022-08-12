package com.coconet.server.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCheckDto {

    private String email;       //이메일
    private String phone;       //전화번호
    private String department;  //부서
    private String position;    //직급
    private String birthDate;   //생년월일
}
