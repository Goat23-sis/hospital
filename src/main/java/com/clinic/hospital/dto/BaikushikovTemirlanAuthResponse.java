package com.clinic.hospital.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaikushikovTemirlanAuthResponse {
    private String token;
    private String username;
    private String role;
}