package com.inno.trainee.controller;

import com.inno.trainee.dto.response.ResponseDtoForStatsList;
import com.inno.trainee.model.CountryPeriodDTO;
import com.inno.trainee.service.CovidStatsService;
import com.inno.trainee.uri.UriPath;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MainController {

  @Inject
  CovidStatsService covidStatsService;

  @GET
  @Path(UriPath.PATH_COUNTRY)
  public Response getCountryStats(@RequestBody CountryPeriodDTO countryPeriodDTO) {
    List<ResponseDtoForStatsList> responseDtoList = covidStatsService.getStatsForCountry(
        countryPeriodDTO);

    return Response.ok(responseDtoList)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @GET
  @Path(UriPath.PATH_WORLD)
  public Response getWorldStats(@RequestBody CountryPeriodDTO countryPeriodDTO) {
    List<ResponseDtoForStatsList> responseDtoList = covidStatsService.getStatsForWorld(
        countryPeriodDTO);

    return Response.ok(responseDtoList)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}
