package com.anirudh.portfolio.aniapp.repository;

import com.anirudh.portfolio.aniapp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> , JpaSpecificationExecutor<Profile> {
}
