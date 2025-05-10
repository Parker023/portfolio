package com.anirudh.portfolio.aniapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SKILL")
@Data
public class Skill {
    @Id
    @SequenceGenerator(name = "skill_generator", allocationSize = 5, sequenceName = "skill_sequence", schema = "portfolio")
    @GeneratedValue(generator = "skill_generator", strategy = GenerationType.SEQUENCE)
    @Column(name = "SKILL_ID")
    private long skillId;
    @Column(name = "SKILL_NAME", nullable = false)
    private String name;
    @Column(name = "SKILL_PROFICIENCY", columnDefinition = "INTEGER DEFAULT 1")
    private int proficiency;
}
