package com.anirudh.portfolio.aniapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
@Data
public class ProfileDTO {
    @NotBlank(message = "first name should not be blank")
    private String firstName;

    private String lastName;

    private String title;

    @NotBlank(message = "about should not be blank")
    private String about;

    @Email(message = "email is not valid")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "phone number is not valid")
    private String phone;

    private String github;
    private String linkedin;

    private List<SkillDTO> skills;
    private List<LanguageProficiencyDTO> languages;

}
