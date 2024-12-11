package org.flight;

import lombok.Getter;

@Getter
public class CrewMember {
  FlightCrewJob job;
  String name;

  CrewMember(FlightCrewJob job, String name) {
    this.job = job;
    this.name = name;
  }
}
