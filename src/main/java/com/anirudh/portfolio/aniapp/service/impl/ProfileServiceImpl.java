package com.anirudh.portfolio.aniapp.service.impl;

import com.anirudh.portfolio.aniapp.dto.*;
import com.anirudh.portfolio.aniapp.exception.InvalidResumeException;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import com.anirudh.portfolio.aniapp.model.LanguageProficiency;
import com.anirudh.portfolio.aniapp.model.Profile;
import com.anirudh.portfolio.aniapp.model.Resume;
import com.anirudh.portfolio.aniapp.model.Skill;
import com.anirudh.portfolio.aniapp.repository.ProfileRepository;
import com.anirudh.portfolio.aniapp.service.ProfileService;
import com.anirudh.portfolio.aniapp.util.GithubUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    @PostConstruct
    private void init() throws ProfileNotFoundException {
        if (profileRepository.count() == 0) {
            ProfileDTO profile = ProfileDTO.builder()
                    .email(defaultProperties.getEmail())
                    .about(defaultProperties.getAbout())
                    .phone(defaultProperties.getPhone())
                    .title(defaultProperties.getTitle())
                    .firstName(defaultProperties.getFirstname())
                    .lastName(defaultProperties.getLastname())
                    .github(defaultProperties.getGithub())
                    .linkedin(defaultProperties.getLinkedin())
                    .build();
            saveProfile(profile);
        }
    }

    private final DefaultProperties defaultProperties;
    private final ProfileRepository profileRepository;

    @Override
    public void saveProfile(ProfileDTO dto) throws ProfileNotFoundException {
        Profile profile = findProfile();
        updateProfileFromDto(profile, dto);
        profileRepository.save(profile);
    }

    private void saveProfile(Profile profile) throws ProfileNotFoundException {
        profileRepository.save(profile);
    }

    @Override
    public ProfileDTO getProfile() throws ProfileNotFoundException {
        return findProfile().toDto();
    }

    @Override
    public void saveProfileWithResume(MultipartFile file) {

        try {
            Resume resume = new Resume();
            resume.setFileName(file.getOriginalFilename());
            resume.setFileType(file.getContentType());
            resume.setFileData(file.getBytes());
            resume.setUploadDate(LocalDateTime.now());
            Profile profile = findProfile();
            profile.setResume(resume);
            saveProfile(profile);
        } catch (IOException | ProfileNotFoundException e) {
            throw new InvalidResumeException(e.getMessage());
        }
    }

    @Override
    public ResumeDTO getResume() throws ProfileNotFoundException {
        return findProfile().getResume().toDTO();
    }

    public Profile findProfile() throws ProfileNotFoundException {
        Optional<Profile> profile = profileRepository.findById(1L);
        return profile.orElseThrow(() -> new ProfileNotFoundException("No Profile Found"));
    }

    public void updateProfileFromDto(Profile profile, ProfileDTO dto) {
        if (dto.getFirstName() != null) profile.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) profile.setLastName(dto.getLastName());
        if (dto.getEmail() != null) profile.setEmail(dto.getEmail());
        if (dto.getPhone() != null) profile.setPhone(dto.getPhone());
        if (dto.getGithub() != null) profile.setGithub(GithubUtil.listToString(dto.getGithub()));
        if (dto.getLinkedin() != null) profile.setLinkedin(dto.getLinkedin());
        if (dto.getTitle() != null) profile.setTitle(dto.getTitle());
        if (dto.getAbout() != null) profile.setAbout(dto.getAbout());
        if (dto.getLanguages() != null)
            profile.setLanguages(updateLanguageFromDto(dto.getLanguages(), profile.getLanguages()));
        if (dto.getSkills() != null)
            profile.setSkills(updateSkillsFromDto(dto.getSkills(), profile.getSkills()));

    }

    private List<Skill> updateSkillsFromDto(List<SkillDTO> dtos, List<Skill> skills) {
        Map<String, Skill> langMap = skills.stream()
                .collect(Collectors.toMap(
                        skill -> skill.getName().toLowerCase(),
                        skill -> skill,
                        (existing, replacement) -> replacement
                ));
        dtos.stream()
                .filter(Objects::nonNull)
                .filter(dto -> langMap.containsKey(dto.getSkillName().toLowerCase()))
                .forEach(dto -> {
                    Skill entity = langMap.get(dto.getSkillName().toLowerCase());
                    entity.setProficiency(dto.getProficiency());
                });

        return new ArrayList<>(langMap.values());
    }

    public List<LanguageProficiency> updateLanguageFromDto(List<LanguageProficiencyDTO> dtos, List<LanguageProficiency> languages) {
        Map<String, LanguageProficiency> langMap = languages.stream()
                .collect(Collectors.toMap(
                        language -> language.getName().toLowerCase(),
                        language -> language,
                        (existing, replacement) -> replacement
                ));
        dtos.stream()
                .filter(Objects::nonNull)
                .filter(dto -> langMap.containsKey(dto.getLanguage().toLowerCase()))
                .forEach(dto -> {
                    LanguageProficiency entity = langMap.get(dto.getLanguage().toLowerCase());
                    entity.setProficiency(dto.getProficiency());
                });

        return new ArrayList<>(langMap.values());
    }

}
