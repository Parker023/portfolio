package com.anirudh.portfolio.aniapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LANGUAGE_PROFICIENCY")
@Data
public class LanguageProficiency {
    @Id
    @SequenceGenerator(name = "lang_prof_generator", allocationSize = 5, sequenceName = "language_proficiency_sequence", schema = "portfolio")
    @GeneratedValue(generator = "lang_prof_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "LANGUAGE_ID")
    private long languageId;
    @Column(name = "LANGUAGE", nullable = false)
    private String name;
    @Column(name = "LANGUAGE_PROFICIENCY", columnDefinition = "INTEGER DEFAULT 1")
    private int proficiency;
}
