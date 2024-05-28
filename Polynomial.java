import java.util.*;
import java.io.*;

public class Polynomial {
  double[] coefficients;
  int[] exponents;
  
  public Polynomial() {
    this.coefficients = new double[0];
    this.exponents = new int[0];
  }

  public Polynomial(double[] coefficients, int[] exponents) {
    this.coefficients = new double[coefficients.length];
    this.exponents = new int[exponents.length];

    for (int i = 0; i < coefficients.length; i++) {
      this.coefficients[i] = coefficients[i];
    }

    for (int i = 0; i < exponents.length; i++) {
      this.exponents[i] = exponents[i];
    }
  }

  public Polynomial(File file) throws FileNotFoundException, IOException {
    BufferedReader input = new BufferedReader(new FileReader(file.getAbsolutePath()));

    String line = input.readLine();

    input.close();
    
    String exprWithPlus = "";

    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == '-') 
        exprWithPlus += "+-";
      else
        exprWithPlus += String.valueOf(line.charAt(i));
    }

    String[] terms = exprWithPlus.split("\\+");

    for (int i = 0; i < terms.length; i++) {
      if (terms[i].indexOf("x") == -1) {
        terms[i] += "x0";
      }
    }

    this.coefficients = new double[terms.length];
    this.exponents = new int[terms.length];

    for (int i = 0; i < terms.length; i++) {
      String[] termsSplit = terms[i].split("x");
      this.coefficients[i] = Double.parseDouble(termsSplit[0]); 
      this.exponents[i] = Integer.parseInt(termsSplit[1]); 
    }


    System.out.println(Arrays.toString(this.coefficients));
    System.out.println(Arrays.toString(this.exponents));
  }

  public Polynomial add(Polynomial p2) {
    int sum_length = this.exponents.length;

    for (int i = 0; i < p2.exponents.length; i++) {
      if (!arrayContains(this.exponents, p2.exponents[i])) sum_length++;
    }

    int[] addedExponents = new int[sum_length]; 
    double[] addedCoeff = new double[sum_length]; 

    int counter = 0;

    for (int i = 0; i < this.exponents.length; i++) {
      addedExponents[i] = this.exponents[i];
      counter++;
    }

    for (int i = 0; i < p2.exponents.length; i++) {
      if (!arrayContains(this.exponents, p2.exponents[i])) {
        addedExponents[counter] = p2.exponents[i];
        counter++;
      }
    }

    for (int i = 0; i < addedExponents.length; i++) {
      int exponent = addedExponents[i];

      int thisIndex = arrayIndex(this.exponents, exponent);
      int p2Index = arrayIndex(p2.exponents, exponent);

      if (thisIndex != -1 && p2Index != -1) {
        addedCoeff[i] = this.coefficients[thisIndex] + p2.coefficients[p2Index];
      } else if (thisIndex != -1) {
        addedCoeff[i] = this.coefficients[thisIndex];
      } else if (p2Index != -1) {
        addedCoeff[i] = p2.coefficients[p2Index];
      }
    }

    int countNonZero = 0;

    for (int i = 0; i < addedCoeff.length; i++) {
      if (addedCoeff[i] != 0) {
        countNonZero++;
      }
    }

    double[] nonZeroAddedCoeff = new double[countNonZero];
    int[] nonZeroAddedExp = new int[countNonZero];

    counter = 0;
    for (int i = 0; i < addedCoeff.length; i++) {
      if (addedCoeff[i] != 0) {
        nonZeroAddedCoeff[counter] = addedCoeff[i];
        nonZeroAddedExp[counter] = addedExponents[i];
        counter++;
      }
    }

    return new Polynomial(nonZeroAddedCoeff, nonZeroAddedExp);
  }

  public Polynomial multiply(Polynomial p2) {
    int[] tempExponents = new int[this.exponents.length * p2.exponents.length];
    double[] tempCoeff = new double[this.exponents.length * p2.exponents.length];
    int counter = 0;

    for (int i = 0; i < this.exponents.length; i++) {
      for (int j = 0; j < this.exponents.length; j++) {
        int thisExp = this.exponents[i];
        int p2Exp = p2.exponents[j];
        double thisCoeff = this.coefficients[i];
        double p2Coeff = p2.coefficients[j];

        if (!arrayContains(tempExponents, thisExp + p2Exp)) {
          tempExponents[counter] = thisExp + p2Exp;
          tempCoeff[counter] = thisCoeff * p2Coeff;
          counter++;
        } else {
          int index = arrayIndex(tempExponents, thisExp + p2Exp);
          tempCoeff[index] += thisCoeff * p2Coeff;
        }
      }
    }

    int[] actualExp = new int[counter + 1];
    double [] actualCoeff = new double[counter + 1];

    for (int i = 0; i < counter + 1; i++) {
      actualExp[i] = tempExponents[i];
      actualCoeff[i] = tempCoeff[i];
    }

    return new Polynomial(actualCoeff, actualExp);
  }

  public void saveToFile(String filename) throws Exception {
    // first we need to parse the polynomial
    
    int len = this.coefficients.length;
    String resultExpression = "";

    for (int i = 0; i < len; i++) {
      String term = "";

      String coeff = String.valueOf(this.coefficients[i]);
      String exp = String.valueOf(this.exponents[i]);

      int decimalIndex = coeff.indexOf(".");

      if ((coeff.substring(decimalIndex + 1)).equals("0")) {
        coeff = coeff.substring(0, decimalIndex);
      }

      if (exp.equals("0"))
        term += coeff;
      else
        term += coeff + "x" + String.valueOf(this.exponents[i]);


      if (i != 0 && term.charAt(0) != '-') 
        term = "+" + term;

      resultExpression += term;
    }

    // parsing complete
    FileWriter writer = new FileWriter(filename);
    writer.write(resultExpression);
    writer.close();

    System.out.println(resultExpression);

  }


  public double evaluate(double x) {
    double answer = 0.0;

    for (int i = 0; i < this.coefficients.length; i++) {
      answer += this.coefficients[i] * Math.pow(x, this.exponents[i]);
    }

    return answer;
  }

  public boolean hasRoot(double x) {
    double answer = this.evaluate(x);
    return answer == 0;
  }

  private static boolean arrayContains(int[] arr, int value) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == value) return true;
    }

    return false;
  }

  private static int arrayIndex(int[] arr, int value) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == value) return i;
    }

    return -1;
  }


}
