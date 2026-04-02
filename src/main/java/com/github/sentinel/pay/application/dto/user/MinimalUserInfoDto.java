package com.github.sentinel.pay.application.dto.user;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinimalUserInfoDto {
    public String name;
    public String role;
}
