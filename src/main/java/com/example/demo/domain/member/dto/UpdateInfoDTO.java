package com.example.demo.domain.member.dto;

import lombok.Data;

@Data
public class UpdateInfoDTO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String memberEmail;
}
