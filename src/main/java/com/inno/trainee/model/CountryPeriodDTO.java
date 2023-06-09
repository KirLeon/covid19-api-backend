package com.inno.trainee.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDateTime;


public record CountryPeriodDTO(@JsonAlias({"countryName", "CountryName"})
                               String countryName,
                               @JsonAlias({"dateFrom", "DateFrom"})
                               LocalDateTime dateFrom,
                               @JsonAlias({"dateTo", "DateTo"})
                               LocalDateTime dateTo) {

}
