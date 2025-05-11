package com.anirudh.portfolio.aniapp.service;

import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import com.anirudh.portfolio.aniapp.model.Resume;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    void saveProfile(ProfileDTO profileDTO) throws ProfileNotFoundException;

    ProfileDTO getProfile() throws ProfileNotFoundException;

    Resume saveProfileWithResume(MultipartFile file);
}
