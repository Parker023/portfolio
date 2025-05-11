package com.anirudh.portfolio.aniapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String message;
    private int status;

    public ResponseDTO(String message, int statusCode) {
        this.message=message;
        this.status=statusCode;
    }
}
