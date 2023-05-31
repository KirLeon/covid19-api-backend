package com.inno.trainee.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDateTime;


public record RequestDtoForCountry(@JsonAlias({"country", "Country"}) String country,
                                   @JsonAlias({"countryCode", "CountryCode"}) String countryCode,
                                   @JsonAlias({"province", "Province"}) String province,
                                   @JsonAlias({"city", "City"}) String city,
                                   @JsonAlias({"cityCode", "CityCode"}) String cityCode,
                                   @JsonAlias({"lat", "Lat"}) String lat,
                                   @JsonAlias({"lon", "Lon"}) String lon,
                                   @JsonAlias({"cases", "Cases"}) int cases,
                                   @JsonAlias({"status", "Status"}) String status,
                                   @JsonAlias({"date", "Date"}) LocalDateTime date) {

}
