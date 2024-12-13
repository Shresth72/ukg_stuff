package org.flight;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.m;

@Getter
@Setter
public class Flight {
  int flightNumber;
  int checkedBags;
  int passengers, seats = 150;

  Flight() {}
  Flight(int k) { this.flightNumber = k; }

  private boolean hasSeating() { return this.passengers < this.seats; }

  // References
  static void swapFlight(Flight i, Flight j) {
    Flight k = i;
    i = j;
    j = k;
  }

  // Copied
  static void swapNumbers(Flight i, Flight j) {
    int k = i.getFlightNumber();
    i.setFlightNumber(j.getFlightNumber());
    j.setFlightNumber(k);
  }

  public void add1Passenger() {
    if (hasSeating())
      this.passengers += 1;
  }

  // Overloading
  public void add1Passenger(int bags) {
    if (hasSeating()) {
      add1Passenger();
      checkedBags += bags;
    }
  }

  public void add1Passenger(int bags, int passenger) {
    add1Passenger(getCheckedBags());
  }

  static void doWork(Object o) {}

  // Cannot be static
  public boolean equals(Object o) {
    Flight flight = (Flight)o;
    return flightNumber == flight.flightNumber;
  }

  // Enums
  void displayJobRespo(FlightCrewJob job) {
    switch (job) {
    case FLIGHT_ATTENDENT:
      System.out.println("Assured passenger safety");
      break;
    case COPILOT:
      System.out.println("Assists in flying the plane");
      break;
    case PILOT:
      System.out.println("Flies the plane");
      break;
    }
  }

  static void whoIsInCharge(CrewMember m1, CrewMember m2) {
    CrewMember theBoss = m1.getJob().compareTo(m2.getJob()) > 0 ? m1 : m2;
    System.out.println(theBoss.getJob().getTitle() + " " + theBoss.getName() +
                       " is boss"); // Captain Geeta is boss
  }

  public static void main(String[] args) {
    System.out.println("Flight Booking");

    Flight val1 = new Flight(10);
    Flight val2 = new Flight(20);
    swapFlight(val1, val2);

    if (val1 == val2)
      System.out.println(false);
    if (val1.equals(val2))
      System.out.println(true);

    // Inheritence
    Object[] stuff = new Object[3];
    stuff[0] = new Flight(123);
    stuff[1] = new CrewMember(FlightCrewJob.PILOT, "Kane");

    Object obj = "Just a string";
    obj = new Flight();
    doWork(obj);

    // Enums
    FlightCrewJob job1 = FlightCrewJob.PILOT;
    FlightCrewJob job2 = FlightCrewJob.FLIGHT_ATTENDENT;

    if (job1 == FlightCrewJob.PILOT)
      System.out.println("job1 is a pilot");
    if (job1 != job2)
      System.out.println("job1 and job2 are different");

    CrewMember geeta = new CrewMember(FlightCrewJob.PILOT, "Geeta");
    CrewMember bob = new CrewMember(FlightCrewJob.FLIGHT_ATTENDENT, "Bob");
    whoIsInCharge(geeta, bob);

    // Record
    Passenger p1 = new Passenger("Bob", 2);
    String n = p1.name();
    int b = p1.checkedBags();
  }
}
