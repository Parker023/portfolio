package com.anirudh.portfolio.aniapp.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
@PropertySource("classpath:mydetails.properties")
@ConfigurationProperties(prefix = "my")
public class DefaultProperties {
    private String firstname;
    private String lastname;
    private String title;
    private String email;
    private String about;
    private String linkedin;
    private List<String> github;
    private String phone;

}
