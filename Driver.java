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
    System.out.println(Arrays.toString(s.exponents));
    System.out.println(Arrays.toString(s.coefficients));

    try {
      s.saveToFile("abc1.txt");
      p1.saveToFile("abc2.txt");
      p2.saveToFile("abc3.txt");
    } catch (Exception e) {
      System.out.println("An error occreed");
    }

    double a = s.evaluate(2);
    System.out.println(a);

    File file = new File("poly.txt");
    System.out.println(file.getAbsolutePath());
    try {
      Polynomial p3 = new Polynomial(file);
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println(e.getMessage());
    }

  }
}
