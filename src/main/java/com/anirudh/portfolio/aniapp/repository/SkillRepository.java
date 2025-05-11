package com.anirudh.portfolio.aniapp.repository;

import com.anirudh.portfolio.aniapp.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
}
