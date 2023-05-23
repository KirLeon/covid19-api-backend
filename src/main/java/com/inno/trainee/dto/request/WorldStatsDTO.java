package com.inno.trainee.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;

public record WorldStatsDTO(@JsonAlias({"NewConfirmed", "newConfirmed"}) int newConfirmed,
                            @JsonAlias({"TotalConfirmed", "totalConfirmed"}) int totalConfirmed,
                            @JsonAlias({"NewDeaths", "newDeaths"}) int newDeaths,
                            @JsonAlias({"TotalDeaths", "totalDeaths"}) int totalDeaths,
                            @JsonAlias({"NewRecovered", "newRecovered"}) int newRecovered,
                            @JsonAlias({"TotalRecovered", "totalRecovered"}) int totalRecovered,
                            @JsonAlias({"Date", "date"}) String date) {

}