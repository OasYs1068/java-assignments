package exercise2;

public class Calculator {
    public double num = 0.0;

    public enum CalMode {
        plus, minus, multiply, divide, normal
    }

    private CalMode mode = CalMode.normal;

    public void plus(Double d) {
        if (ifNormal(d)) {
            mode = CalMode.plus;
            return;
        }
        num += d;
        mode = CalMode.plus;
    }

    public void minus(Double d) {
        if (ifNormal(d)) {
            mode = CalMode.minus;
            return;
        }
        num -= d;
        mode = CalMode.minus;
    }

    public void multiply(Double d) {
        if (ifNormal(d)) {
            mode = CalMode.multiply;
            return;
        }
        num *= d;
        mode = CalMode.multiply;
    }

    public void divide(Double d) {
        if (ifNormal(d)) {
            mode = CalMode.divide;
            return;
        }
        num /= d;
        mode = CalMode.divide;
    }

    public void equal(Double d) {
        switch (mode) {
            case plus:
                plus(d);
                break;
            case minus:
                minus(d);
                break;
            case divide:
                divide(d);
                break;
            case multiply:
                multiply(d);
                break;
            case normal:
                num = d;
                break;
        }
        mode = CalMode.normal;
    }

    public boolean ifNormal(Double d) {
        if (mode == CalMode.normal) {
            num = d;
            return true;
        }
        return false;
    }
}