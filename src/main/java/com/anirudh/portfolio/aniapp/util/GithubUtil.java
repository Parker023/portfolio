package com.anirudh.portfolio.aniapp.util;

import com.anirudh.portfolio.aniapp.constants.PortfolioConstants;

import java.util.Arrays;
import java.util.List;

public class GithubUtil {
    public static String listToString(List<String> links) {
        return String.join(PortfolioConstants.GITHUB_DELIMITER, links);
    }

    public static List<String> stringToList(String links) {
        return Arrays.asList(links.split(PortfolioConstants.GITHUB_DELIMITER));
    }


}
