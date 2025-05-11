package com.anirudh.portfolio.aniapp.service;

import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import com.anirudh.portfolio.aniapp.dto.ResumeDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    void saveProfile(ProfileDTO profileDTO) throws ProfileNotFoundException;

    ProfileDTO getProfile() throws ProfileNotFoundException;

    void saveProfileWithResume(MultipartFile file);

    ResumeDTO getResume() throws ProfileNotFoundException;
}
