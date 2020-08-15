package main.java.market;

public interface IStock {

    public String getName();

    public float getPrice();

    public int getAvailableShares();

    public void adjustAvailableShares(int amount);

    public void adjustPrice(float amount);

    public void fluctuate();

}