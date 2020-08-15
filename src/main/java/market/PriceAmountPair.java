package main.java.market;

public class PriceAmountPair<Float, Integer> {
    private final Float p;
    private final Integer a;

    public PriceAmountPair(Float p, Integer a) {
        this.p = p;
        this.a = a;
    }

    public Float price() {
        return this.p;
    }

    public Integer amount() {
        return this.a;
    }

    public float totalValue() {
        return (float) this.p * (float) this.a;
    }
}
