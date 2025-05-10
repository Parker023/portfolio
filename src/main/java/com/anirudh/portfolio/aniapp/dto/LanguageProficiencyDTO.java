package com.anirudh.portfolio.aniapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LanguageProficiencyDTO {
    @NotBlank(message = "language should not be blank")
    private String language;
    private int proficiency;
}
