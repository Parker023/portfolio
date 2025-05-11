package com.anirudh.portfolio.aniapp.service;

import com.anirudh.portfolio.aniapp.dto.DefaultProperties;
import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import com.anirudh.portfolio.aniapp.model.Profile;
import com.anirudh.portfolio.aniapp.repository.ProfileRepository;
import com.anirudh.portfolio.aniapp.util.GithubUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @PostConstruct
    private void init() {
        Profile profile = Profile.builder().profileId(1L)
                .email(defaultProperties.getEmail())
                .about(defaultProperties.getAbout())
                .phone(defaultProperties.getPhone())
                .title(defaultProperties.getTitle())
                .firstName(defaultProperties.getFirstname())
                .lastName(defaultProperties.getLastname())
                .github(GithubUtil.listToString(defaultProperties.getGithub()))
                .build();
        saveProfile(profile);
    }

    private final DefaultProperties defaultProperties;
    private final ProfileRepository profileRepository;

    public ProfileService(DefaultProperties defaultProperties, ProfileRepository profileRepository) {
        this.defaultProperties = defaultProperties;
        this.profileRepository = profileRepository;
    }

    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public ProfileDTO getProfile() throws ProfileNotFoundException {
        Optional<ProfileDTO> profileDTO = profileRepository.findById(1L).map(Profile::toDto);
        return profileDTO.orElseThrow(() -> new ProfileNotFoundException("No Profile Found"));
    }


}
