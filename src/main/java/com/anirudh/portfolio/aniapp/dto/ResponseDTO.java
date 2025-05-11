package com.anirudh.portfolio.aniapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String message;
    private int status;
    private ProfileDTO profileDTO;

    public ResponseDTO(String message, int statusCode, ProfileDTO profileDTO) {
        this.message = message;
        this.status = statusCode;
        this.profileDTO = profileDTO;
    }
}
