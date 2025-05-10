package com.anirudh.portfolio.aniapp.service;

import com.anirudh.portfolio.aniapp.model.Profile;
import com.anirudh.portfolio.aniapp.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }

    public Optional<Profile> getProfile() {
        Profile profile = profileRepository.findAll().getFirst();
        return Optional.ofNullable(profile);
    }


}
