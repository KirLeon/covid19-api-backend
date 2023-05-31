package com.inno.trainee.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://api.covid19api.com")
public interface CovidClient {

  @GET
  @Path("/country/{country}/status/confirmed")
  Response getCountryStats(@PathParam("country") String country,
      @QueryParam("from") LocalDateTime dateFrom, @QueryParam("to") LocalDateTime dateTo);

  @GET
  @Path("/world")
  Response getWorldStats(@QueryParam("from") LocalDateTime dateFrom,
      @QueryParam("to") LocalDateTime dateTo);
}
