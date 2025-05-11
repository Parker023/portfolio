package com.anirudh.portfolio.aniapp.model;

import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PROFILE", schema = "portfolio")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Profile {
    @Id
    @Builder.Default
    @Column(name = "PROFILE_ID")
    private long profileId=1L;
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;
    @Column(name = "TITLE", length = 100)
    private String title;
    @Column(name = "ABOUT", nullable = false)
    private String about;
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PHONE", nullable = false)
    private String phone;
    @Column(name = "GITHUB_LINK")
    private String github;
    @Column(name = "LINKEDIN_PROFILE")
    private String linkedin;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PROFILE_ID_FK", referencedColumnName = "PROFILE_ID")
    private List<Skill> skills;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PROFILE_ID_FK", referencedColumnName = "PROFILE_ID")
    private List<LanguageProficiency> languages;

    public ProfileDTO toDto() {
        ProfileDTO dto = new ProfileDTO();
        dto.setAbout(this.getAbout());
        dto.setEmail(this.getEmail());
        dto.setGithub(this.getGithub());
        dto.setPhone(this.getPhone());
        dto.setFirstName(this.getFirstName());
        dto.setLastName(this.getLastName());
        dto.setLinkedin(this.getLinkedin());
        dto.setTitle(this.getTitle());
        dto.setLanguages(this.getLanguages().stream().map(LanguageProficiency::toDto).toList());
        dto.setSkills(this.getSkills().stream().map(Skill::toDto).toList());
        return dto;
    }
}
