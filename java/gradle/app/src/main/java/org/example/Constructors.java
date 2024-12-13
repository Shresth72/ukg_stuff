package org.example;

public class Constructors {
  long circuMiles = 2409;
  long circmKms = Math.round(circuMiles * 1.6d);
  int passengers, seats = 150;
  int freeBags, checkedBags;
  double perBagFree;
  private boolean[] isSeatAval = new boolean[seats];

  // Static
  private static int allPassengers, maxPassengersPerFlight;

  public static int getAllPassengers() { return allPassengers; }
  public static void resetAllPassengers() { allPassengers = 0; }

  static {
    Strings admin = new Strings();
    admin.concat();
    maxPassengersPerFlight = Integer.MAX_VALUE;
  }

  public void add1Passenger() {
    if (passengers < seats && passengers < maxPassengersPerFlight) {
      passengers += 1;
      allPassengers += 1;
    }
  }

  // Constructors
  public Constructors() {
    for (int i = 0; i < this.seats; i++)
      isSeatAval[i] = true;
  }

  public Constructors(int freeBags) {
    this(freeBags > 1 ? 25.0d : 50.0d);
    this.freeBags = freeBags;
  }

  public Constructors(int freeBags, int checkbags) {
    this(freeBags); // this.freeBags = checkbags;
    this.checkedBags = checkbags;
  }

  private Constructors(double perBagFree) {
    this.perBagFree = perBagFree;
    return;
  }

  public static void main(String[] args) {
    System.out.println("This is main");
    System.out.println("Constructors");
    System.out.println();

    Constructors cheapJoe = new Constructors(0.01d);
    Constructors geeta = new Constructors(2);
    System.out.println(cheapJoe.freeBags);
    System.out.println(geeta.freeBags);

    Constructors allSeats = new Constructors();
    System.out.println(allSeats.isSeatAval[0]);
  }
}
