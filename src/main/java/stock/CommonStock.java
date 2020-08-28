package main.java.stock;

public abstract class CommonStock implements IStock {

    protected final String name;
    protected float price;
    protected int availableShares;

    public CommonStock(String name, float initPrice, int initShares) {
        this.name = name;
        this.price = initPrice;
        this.availableShares = initShares;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public int getAvailableShares() {
        return this.availableShares;
    }

    @Override
    public void adjustAvailableShares(int amount) {
        this.availableShares = this.availableShares + amount;
    }

    @Override
    public void adjustPrice(float amount) {
        this.price = this.price + amount;
    }

}
