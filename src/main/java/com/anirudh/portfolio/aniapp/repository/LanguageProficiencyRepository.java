package com.anirudh.portfolio.aniapp.repository;

import com.anirudh.portfolio.aniapp.model.LanguageProficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageProficiencyRepository extends JpaRepository<LanguageProficiency,Long> {
}
