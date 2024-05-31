import java.util.*;
import java.io.*;

public class Driver {
  public static void main(String[] args) {
    double[] c1 = {1, 1};
    int[] e1 = {1, 0};
    Polynomial p1 = new Polynomial(c1, e1);
    double[] c2 = {1, 2};
    int[] e2 = {1, 0};
    Polynomial p2 = new Polynomial(c2, e2);

    Polynomial s = p1.multiply(p2);

    try {
      s.saveToFile("abc1.txt");
      p1.saveToFile("abc2.txt");
      p2.saveToFile("abc3.txt");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    double a = s.evaluate(2);
    System.out.println(a);

    File file = new File("poly.txt");
    try {
      Polynomial p3 = new Polynomial(file);
      System.out.println("Exponents: " + Arrays.toString(p3.exponents));
      System.out.println("Coeff: " + Arrays.toString(p3.coefficients));
    } catch (Exception e) {
      System.out.println(e.getMessage() + "dubey");
    }

  }
}
