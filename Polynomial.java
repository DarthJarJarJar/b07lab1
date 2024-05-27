public class Polynomial {
  double[] coefficients;
  double[] exponents;
  
  public Polynomial() {
    this.coefficients = new double[1];
    this.coefficients[0] = 0;

    this.exponents = new double[1];
    this.exponents[0] = 0;
  }

  public Polynomial(double[] coefficients, double[] exponents) {
    this.coefficients = new double[coefficients.length];
    this.exponents = new double[exponents.length];

    for (int i = 0; i < coefficients.length; i++) {
      this.coefficients[i] = coefficients[i];
    }

    for (int i = 0; i < exponents.length; i++) {
      this.exponents[i] = exponents[i];
    }
  }

  public Polynomial add(Polynomial p2) {
    int sum_length = Math.max(this.coefficients.length, p2.coefficients.length);

    double[] sumCoefficients = new double[sum_length];

    for (int i = 0; i < sum_length; i++) {
      if (i < p2.coefficients.length && i < this.coefficients.length) {
        sumCoefficients[i] = p2.coefficients[i] + this.coefficients[i];
      } else if(i < p2.coefficients.length) {
        sumCoefficients[i] = p2.coefficients[i];
      } else if (i < this.coefficients[i]) {
        sumCoefficients[i] = this.coefficients[i];
      } 
    }

    Polynomial sum = new Polynomial(sumCoefficients);

    return sum;
  }

  public double evaluate(double x) {
    double answer = 0.0;

    for (int i = 0; i < this.coefficients.length; i++) {
      answer += this.coefficients[i] * Math.pow(x, i);
    }

    return answer;
  }

  public boolean hasRoot(double x) {
    double answer = this.evaluate(x);
    return answer == 0;
  }

}
