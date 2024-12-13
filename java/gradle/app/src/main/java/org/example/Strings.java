package org.example;

import java.util.Scanner;

public class Strings {
  public void concat() {
    String name = "Jim";
    String greeting = "Hello " + name;
    System.out.println(greeting);
  }

  public void equality() {
    String s1 = "I love";
    s1 += " Java";
    String s2 = "I love Java";
    if (s1 == s2)
      System.out.println("Huh");
    else if (s1.equals(s2))
      System.out.println("Yes s1 = s2");
    else
      System.out.println("No");

    String s3 = s1.intern();
    String s4 = s1.intern(); // s4 references the intern s3
    if (s3 == s4)
      System.out.println("Yes s3 = s4");
    else
      System.out.println("No");
  }

  public void stringMethods() {
    String s1 = "Java  ";
    s1.length();

    // Create new string
    s1.concat("k");
    s1.replace("Jva", "Java");
    s1.toLowerCase();
    s1.toUpperCase();
    s1.split(".");
    s1.trim();

    // Extract substring
    s1.charAt(1);
    s1.substring(3);

    // Test substring
    s1.contains("tiger");
    s1.endsWith("a");
    s1.startsWith("J");
    s1.indexOf("v");
    s1.lastIndexOf("a");

    // Comparisons
    s1.equals("Jav");
    s1.equalsIgnoreCase("jav");
    s1.isEmpty();
    s1.compareTo("ja");
    s1.compareToIgnoreCase("ja");

    // Formating
    // s1.format("k", "k");

    // Converting non-string to string
    int iVal = 100;
    String sVal = String.valueOf(iVal); // "100"

    int i = 2, j = 3;
    int res = i * j;
    String output = i + " * " + j + " = " + res; // 2*3=6
                                                 //
    System.out.println(sVal + output);
  }

  public static void stringBuilder() {
    String location = "Florida";
    int flight = 175;

    StringBuilder sb = new StringBuilder(40);
    sb.append("I flew to ");
    sb.append(location);
    sb.append(" on Flight: ");
    sb.append(flight);
    String msg = sb.toString();
    System.out.println(msg);

    String time = "9:00";
    int pos = sb.indexOf(" on");
    sb.insert(pos, " at ");
    sb.insert(pos + 4, time);

    System.out.println(sb.toString());
  }

  public static void main(String[] args) {
    // Strings hold a reference to the instance of the string
    // They are immutable

    Strings stringsInst = new Strings();
    stringsInst.concat();
    stringsInst.equality();
    stringsInst.stringMethods();

    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();
    String[] parts = userInput.split(" ");
    System.out.println(parts);

    scanner.close();
  }
}
