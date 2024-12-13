package org.example;

public class Methods {
  public static void main(String[] args) {
    System.out.println("This is main");
    System.out.println("Method");

    // If Main is static
    Methods methodsInst = new Methods();
    float v1 = 7.5f;
    float v2 = 1.4f;
    // parameters are copied over
    methodsInst.showSomeMethods(v1, v2, 3);

    // If Main is not static
    // showSomeMethods(7.5f, 1.4f, 3);

    double[] k = methodsInst.produceArray(8, 2);
    for (int i = 0; i < 2; i++) {
      System.out.println(k[i]);
    }
  }

  public void showSomeMethods(float x, float y, int count) {
    float sum = x + y;
    for (int i = 0; i < count; i++)
      System.out.println(sum);

    return;
  }

  public double[] produceArray(double amt, int years) {
    double[] inter = new double[years];
    for (int i = 0; i < years; i++) {
      int year = i + 1;
      inter[i] = year * amt;
    }

    return inter;
  }
}
