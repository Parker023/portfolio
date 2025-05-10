package com.anirudh.portfolio.aniapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillDTO {
    @NotBlank(message = "Skill name should not be blank")
    private String skillName;
    private int proficiency;

}
