package main.java.market;

import java.util.Random;

public class RisingStock extends CommonStock implements IStock {

    private final float initPrice;

    public RisingStock(float initPrice, int initShares) {
        super("Rising Stock", initPrice, initShares);
        this.initPrice = initPrice;
    }

    @Override
    public void fluctuate() {
        Random rand = new Random();
        if (rand.nextInt(10) < 6) {
            this.adjustPrice(this.initPrice * (float) 0.01);
        } else {
            this.adjustPrice(-this.initPrice * (float) 0.01);
        }
    }

}
