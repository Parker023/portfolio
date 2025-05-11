package com.anirudh.portfolio.aniapp.service.impl;

import com.anirudh.portfolio.aniapp.dto.DefaultProperties;
import com.anirudh.portfolio.aniapp.dto.LanguageProficiencyDTO;
import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import com.anirudh.portfolio.aniapp.model.LanguageProficiency;
import com.anirudh.portfolio.aniapp.model.Profile;
import com.anirudh.portfolio.aniapp.repository.ProfileRepository;
import com.anirudh.portfolio.aniapp.service.ProfileService;
import com.anirudh.portfolio.aniapp.util.GithubUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {
    @PostConstruct
    private void init() throws ProfileNotFoundException {
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

    private final DefaultProperties defaultProperties;
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(DefaultProperties defaultProperties, ProfileRepository profileRepository) {
        this.defaultProperties = defaultProperties;
        this.profileRepository = profileRepository;
    }

    @Override
    public void saveProfile(ProfileDTO dto) throws ProfileNotFoundException {
        Profile profile = findProfile();
        updateProfileFromDto(profile, dto);
        profileRepository.save(profile);
    }

    @Override
    public ProfileDTO getProfile() throws ProfileNotFoundException {
        return findProfile().toDto();
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

    }

    public List<LanguageProficiency> updateLanguageFromDto(List<LanguageProficiencyDTO> dtos, List<LanguageProficiency> languages) {
        Map<String, LanguageProficiency> langMap = languages.stream()
                .map(dto -> {
                    dto.setName(dto.getName().toLowerCase());
                    return dto;
                })
                .collect(Collectors.toMap(
                        LanguageProficiency::getName,
                        dto -> dto,
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
