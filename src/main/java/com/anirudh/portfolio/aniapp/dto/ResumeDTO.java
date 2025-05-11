package com.anirudh.portfolio.aniapp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResumeDTO {
    private String resumeName;
    private String resumeSize;
    private String resumeType;
    private byte[] bytes;
}
