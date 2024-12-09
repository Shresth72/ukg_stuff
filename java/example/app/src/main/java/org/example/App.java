package org.example;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unused")
@Getter
@Setter
public class App {
  private String name;

  public String getGreeting() {
    // Using a Setter and Getter
    setName("Hello World");
    String hello = getName();
    return hello;
  }

  public Integer variables() {
    // Mutable
    int myVar;
    myVar = 50;
    System.out.println(myVar);

    int anotherVar = 100;
    System.out.println(anotherVar);

    myVar = anotherVar;

    // Immutable
    final int maxStudents = 25;

    final int someVariable;
    int someOtherVariable = 100;
    someVariable = someOtherVariable;
    System.out.println(someVariable);

    return maxStudents;
  }

  public void dataTypes() {
    byte num01 = 8; // bits
    short num02 = 16;
    int num03 = 32;
    long num04 = 64L;

    float f01 = 32.00f;
    double f02 = 64.00d;

    char regularU = 'U';
    char accentedU = '\u00DA';

    boolean type = true;

    // Storing
    // Primitives types are stored by value
    int firstValue = 100;
    int otherValue = firstValue; // Copied value
  }

  public void typeConversion() {
    long longValueOne = 50;
    int intValueOne = (int)longValueOne;

    // Implicit
    float floatV = 1.0f;
    double doubleV = 4.0d;
    byte byteV = 7;
    short shortV = 7;
    long longV = 5;

    short r1 = byteV;
    r1 = (short)longV;
    r1 = (short)(byteV - longV);

    float r2 = (longV - floatV);

    // Type infer
    var v1 = 50;
    var myValue = 100.0;
    int i = 25;
    var total = i + v1;

    var v2 = 50.0;
    v2 = (int)100.0;

    var v3 = 7.5f;
    v2 = (int)v3;
  }

  public void conditionalStatements() {
    int v1 = 7, v2 = 5;
    int maxVal = v1 > v2 ? v1 : v2;

    if (v1 > v2)
      System.out.println(v1);
    else if (v2 > v1)
      System.out.println(v2);
    else
      System.out.println("nope");

    // Block Statements
    final int diff;
    if (v1 > v2) {
      diff = v1 - v2;
      System.out.println("v1: " + diff);
    } else {
      diff = v2 - v1;
      System.out.println("v2: " + diff);
    }

    // Switch
    char opcode = 'd';
    int result = 0;
    switch (opcode) {
    case 'a':
      result = v1 + v2;
      break;
    case 'b':
      result = v1 - v2;
      break;
    default:
      System.out.println(opcode);
      result = 404;
    }
    System.out.println(result);
  }

  public void loops() {
    int someV = 4;
    int f = 1;
    while (someV > 1) {
      f += 1;
      someV--;
      System.out.println(f);
    }

    for (int i = 1; i < 100; i *= 2) {
      System.out.println(i);
    }
  }

  public void arrays() {
    float[] vals = new float[3];
    vals[0] = 10.0f;
    vals[1] = 20.5f;
    vals[2] = 42.5f;

    float sum = 0.0f;
    for (int i = 0; i < vals.length; i++) {
      sum += vals[i];
    }
    System.out.println(sum);

    for (float curVal : vals) {
      sum += curVal;
    }
    System.out.println(sum);
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());

    if (args.length < 1) {
      System.out.println("Nope");
    } else {
      // for (String arg : args)
      //   System.out.println(arg);

      String arg1 = args[0];
      if (arg1.length() == 1) {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        System.out.println("\033[33mopcode:\033[0m " + opCode +
                           ", \033[33mleftVal:\033[0m " + leftVal);
      } else {
        System.out.println("\033[31mError\033[0m: Opcode should be a char");
      }
    }
  }
}
