package org.annoatations;

public class Doer {
  @Deprecated
  public void doTheThing() {
    System.out.println("Old Way");
  }

  public void doTheThingNew() { System.out.println("New Way"); }
}
