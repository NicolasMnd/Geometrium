package beziercurves;

public class BernsteinPolynomal {

    private final int degree, n;

    public BernsteinPolynomal(int degree, int n) {
        this.degree = degree;
        this.n = n;
    }

    public float f(float constant, float value) {
        return (float) ((float) combination(n, degree) * Math.pow(value, degree) * Math.pow(1-value, n - degree) * constant);
    }

    private int combination(int n, int i) {
        return factorial(n) / (factorial(i) * factorial(n-i));
    }

    private int factorial(int num) {
        if(num == 0) return 1;
        else if(num < 0)
            return -1;
        else {
            int fact = 1;
            for(int i = num; i > 0; i--) {
                fact *= i;
            }
            return fact;
        }
    }

}
