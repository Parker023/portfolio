package com.anirudh.portfolio.aniapp.controller;

import com.anirudh.portfolio.aniapp.dto.ProfileDTO;
import com.anirudh.portfolio.aniapp.dto.ResponseDTO;
import com.anirudh.portfolio.aniapp.dto.ResumeDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import com.anirudh.portfolio.aniapp.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping("/resume")
    public ResponseEntity<ResumeDTO> uploadResume(@RequestParam MultipartFile file) throws ProfileNotFoundException {
        profileService.saveProfileWithResume(file);
        ResumeDTO resume = profileService.getProfile().getResumeDTO();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resume.getResumeName() + "\"")
                .body(resume);
    }

    @GetMapping("/resume")
    public ResponseEntity<ResumeDTO> getResume() throws ProfileNotFoundException {
        ResumeDTO resume = profileService.getResume();

        return ResponseEntity.ok(resume);
    }
}
