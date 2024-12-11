package org.flight;

import lombok.Getter;

@Getter
public enum FlightCrewJob {
  FLIGHT_ATTENDENT("Flight Attendent"),
  COPILOT("First Officer"),
  PILOT("Captain");

  private FlightCrewJob() {}

  private String title;
  private FlightCrewJob(String title) { this.title = title; }
}
