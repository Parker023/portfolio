package com.anirudh.portfolio.aniapp.service;

import com.anirudh.portfolio.aniapp.model.Profile;
import com.anirudh.portfolio.aniapp.repository.LanguageProficiencyRepository;
import com.anirudh.portfolio.aniapp.repository.ProfileRepository;
import com.anirudh.portfolio.aniapp.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class ProfileService {
    private final SkillRepository skillRepository;
    private final ProfileRepository profileRepository;
    private final LanguageProficiencyRepository languageProficiencyRepository;

    public ProfileService(SkillRepository skillRepository, ProfileRepository profileRepository, LanguageProficiencyRepository languageProficiencyRepository) {
        this.skillRepository = skillRepository;
        this.profileRepository = profileRepository;
        this.languageProficiencyRepository = languageProficiencyRepository;
    }

    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }


}
