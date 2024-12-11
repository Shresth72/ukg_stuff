package org.annoatations;

public class MyWorker {
  @SuppressWarnings("deprecation")
  void doSomething() {
    Doer d1 = new Doer();
    d1.doTheThing();
  }

  void doDoubleWork() {
    Doer d2 = new Doer();
    d2.doTheThing(); // Still gives warning
    d2.doTheThingNew();
  }

  @Override
  public String toString() {
    return "";
  }

  public static void main(String[] args) {}
}
