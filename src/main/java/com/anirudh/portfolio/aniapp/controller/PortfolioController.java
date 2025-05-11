package com.anirudh.portfolio.aniapp.controller;

import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import com.anirudh.portfolio.aniapp.dto.ResponseDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import com.anirudh.portfolio.aniapp.model.Resume;
import com.anirudh.portfolio.aniapp.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/portfolio")
@RestController
@RequiredArgsConstructor
public class PortfolioController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<ResponseDTO> getProfile() throws ProfileNotFoundException {
        ProfileDTO profile = profileService.getProfile();
        return ResponseEntity.ofNullable(ResponseDTO.builder()
                .message("fetched profile successfully")
                .profileDTO(profile)
                .status(HttpStatus.OK.value())
                .build());
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponseDTO> updateProfile(@Valid @RequestBody ProfileDTO profileDTO) throws ProfileNotFoundException {
        profileService.saveProfile(profileDTO);
        ProfileDTO dto = profileService.getProfile();
        return ResponseEntity.ofNullable(ResponseDTO.builder()
                .message("profile updated successfully")
                .profileDTO(dto)
                .status(HttpStatus.OK.value())
                .build());
    }
    @PostMapping("/{profileId}/resume")
    public ResponseEntity<Resume> uploadResume(@PathVariable Long profileId, @RequestParam MultipartFile file) throws IOException {
        // Save the Resume and associate it with the Profile
        Resume uploadedResume = profileService.saveProfileWithResume(file);

        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedResume);
    }

    @GetMapping("/{profileId}/resume")
    public ResponseEntity<Resume> getResume(@PathVariable Long profileId) {
        // Retrieve the Resume for the Profile
        Resume resume = profileService.getResume(profileId);

        return ResponseEntity.ok(resume);
    }
}
