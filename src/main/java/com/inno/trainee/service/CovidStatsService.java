package com.inno.trainee.service;

import com.inno.trainee.client.CovidClient;
import com.inno.trainee.dto.request.RequestDtoForCountry;
import com.inno.trainee.dto.request.RequestDtoForWorld;
import com.inno.trainee.dto.response.ResponseDtoForStatsList;
import com.inno.trainee.model.CountryPeriodDTO;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Singleton
public class CovidStatsService {

  @Inject
  @RestClient
  CovidClient covidClient;

  public List<ResponseDtoForStatsList> getStatsForCountry(CountryPeriodDTO countryPeriodDTO) {

    validateDate(countryPeriodDTO);

    Response clientResponse = covidClient.getCountryStats(
        countryPeriodDTO.countryName(),
        countryPeriodDTO.dateFrom(), countryPeriodDTO.dateTo());
    validateResponse(clientResponse);

    List<RequestDtoForCountry> requestDtoList = clientResponse.readEntity(new GenericType<>() {
    });

    return requestDtoList.stream()
        .sorted(Comparator.comparing(RequestDtoForCountry::date))
        .map(dto -> new ResponseDtoForStatsList(dto.date(), dto.cases()))
        .collect(Collectors.toList());
  }

  public List<ResponseDtoForStatsList> getStatsForWorld(CountryPeriodDTO countryPeriodDTO) {

    validateDate(countryPeriodDTO);

    Response clientResponse = covidClient.getWorldStats(
        countryPeriodDTO.dateFrom(), countryPeriodDTO.dateTo());
    validateResponse(clientResponse);

    List<RequestDtoForWorld> requestDtoList = clientResponse.readEntity(new GenericType<>() {
    });

    return requestDtoList.stream()
        .sorted(Comparator.comparing(RequestDtoForWorld::date))
        .map(dto -> new ResponseDtoForStatsList(dto.date(), dto.newConfirmed()))
        .collect(Collectors.toList());
  }

  public void validateDate(CountryPeriodDTO countryPeriodDTO) {
    if (countryPeriodDTO.dateFrom().isAfter(countryPeriodDTO.dateTo())) {
      throw new WebApplicationException(
          "Incorrect date: the start date should go earlier than the end date", Status.BAD_REQUEST);
    }
  }

  public void validateResponse(Response response) {
    if (response == null || response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
      throw new WebApplicationException("Error: not found");
    }
  }
}
