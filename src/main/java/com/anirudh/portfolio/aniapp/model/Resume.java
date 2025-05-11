package com.anirudh.portfolio.aniapp.model;

import com.anirudh.portfolio.aniapp.dto.ResumeDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESUME", schema = "portfolio")
@Data
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @Lob
    private byte[] fileData;

    private String fileType;
    private LocalDateTime uploadDate;

    public ResumeDTO toDTO() {
        return ResumeDTO.builder()
                .bytes(this.getFileData())
                .resumeName(this.getFileName())
                .resumeType(this.getFileType())
                .resumeSize(FileUtils.byteCountToDisplaySize(this.fileData.length))
                .build();
    }
}
