package gt.util;

public class DoublePair {
    private final double a;
    private final double b;

    public DoublePair(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getFirst() {
        return a;
    }

    public double getSecond() {
        return b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        long temp = Double.doubleToLongBits(a);
        int result = prime + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        return prime * result + (int) (temp ^ (temp >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DoublePair other = (DoublePair) obj;
        return a == other.a && b == other.b;
    }

    @Override
    public String toString() {
        return a + ", " + b;
    }
}
