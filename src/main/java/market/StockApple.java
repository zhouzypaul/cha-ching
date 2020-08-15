package main.java.market;

import java.util.Random;

public class StockApple extends CommonStock implements IStock {

    public StockApple(float initPrice, int initShares) {
        super("Apple", initPrice, initShares);
    }

    @Override
    public void fluctuate() {
        Random rand = new Random();
        this.adjustPrice((rand.nextInt(3) - 1) * (this.getPrice() / 100));
    }
}
