package org.example;

public class Classes {
  int passenger;
  private int seats = 200;

  public void flight() {
    System.out.println(passenger);
    return;
  }

  public void add1Passender() {
    if (this.passenger < seats)
      this.passenger += 1;
  }

  public void setSeats(int seats) {
    this.seats = seats;
    return;
  }

  private static Classes create(int pass) {
    Classes eq = new Classes();
    eq.passenger = pass;
    return eq;
  }

  public static void main(String[] args) {
    System.out.println("This is main");
    System.out.println("Method");

    Classes flight1;
    flight1 = new Classes();
    flight1.passenger = 1;

    Classes flight2;
    flight2 = new Classes();
    flight2.passenger = 2;

    flight1.flight();
    flight2 = flight1;

    flight2.flight();

    // Array of classes
    Classes[] equations = new Classes[4];
    equations[0] = create(20);
    equations[1] = create(40);
    equations[2] = create(60);
    equations[3] = create(100);

    for (Classes eq : equations) {
      eq.flight();
    }
  }
}
