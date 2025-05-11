package com.anirudh.portfolio.aniapp.service;

import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;

public interface ProfileService {
    void saveProfile(ProfileDTO profileDTO) throws ProfileNotFoundException;

    ProfileDTO getProfile() throws ProfileNotFoundException;

}
