package com.fullsecurity.fullsecurity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserInfoResponse {

    private Long id;

    private String username;

    private String email;

    private List<String> roles;

    private String token;
}
