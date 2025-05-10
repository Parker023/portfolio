package com.anirudh.portfolio.aniapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "PROFILE")
@Data
@ToString
public class Profile {
    @Id
    @SequenceGenerator(name = "profile_generator", initialValue = 1, allocationSize = 5, sequenceName = "profile_sequence",schema = "portfolio")
    @GeneratedValue(generator = "profile_generator", strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "FIRST_NAME",nullable = false,length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;
    @Column(name = "TITLE", length = 100)
    private String title;
    @Column(name = "ABOUT",nullable = false)
    private String about;
    @Column(name = "EMAIL",nullable = false)
    private String email;
    @Column(name = "PHONE",nullable = false)
    private String phone;
    @Column(name = "GITHUB_LINK")
    private String github;
    @Column(name = "LINKEDIN_PROFILE")
    private String linkedin;


}
