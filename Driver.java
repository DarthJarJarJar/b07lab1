import java.util.*;

import java.io.*;

public class Driver {
  public static void main(String[] args) {
    double[] c1 = {1, 1};
    int[] e1 = {1, 0};
    Polynomial p1 = new Polynomial(c1, e1);
    double[] c11 = {-1, -1};
    int[] e11 = {1, 0};
    Polynomial p11 = new Polynomial(c11, e11);
    double[] c2 = {1, 2};
    int[] e2 = {1, 0};
    Polynomial p2 = new Polynomial(c2, e2);

    Polynomial s = p1.multiply(p2);
    Polynomial d = new Polynomial();
    Polynomial zeroSum = p1.add(p11);
    System.out.println(Arrays.toString(zeroSum.coefficients));
    System.out.println(Arrays.toString(zeroSum.exponents));
    if (Polynomial.isZeroPolynomial(zeroSum)) System.out.println("Zero sum works");

    try {
      s.saveToFile("abc1.txt");
      p1.saveToFile("abc2.txt");
      p2.saveToFile("../polynomial.txt");
      d.saveToFile("../dubey.txt");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    double a = s.evaluate(2);
    System.out.println(a);

    File file = new File("poly.txt");
    File file2 = new File("empty.txt");
    try {
      Polynomial p3 = new Polynomial(file);
      //System.out.println("Exponents: " + Arrays.toString(p3.exponents));
      //System.out.println("Coeff: " + Arrays.toString(p3.coefficients));

      Polynomial empty = new Polynomial(file2);
      if (Polynomial.isZeroPolynomial(empty)) System.out.println("zero polynomial is working");
      if (Polynomial.isZeroPolynomial(empty.add(empty))) System.out.println("zero polynomial is working");
      if (Polynomial.isZeroPolynomial(empty.multiply(p3))) System.out.println("zero polynomial is working");
      if (empty.hasRoot(15) == true) System.out.println("zero polynomial is working");
      if (empty.evaluate(15) == 0) System.out.println("zero polynomial is working");
    } catch (Exception e) {
      System.out.println(e.getMessage() + "dubey");
    }

  }
}
