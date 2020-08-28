package main.java.stock;

import java.util.Random;

/**
 * the normal stock fluctuates according to a normal distribution N(0, 1)
 */
public class NormalStock extends CommonStock implements IStock {

    private final float initPrice;

    public NormalStock(float initPrice, int initShares) {
        super("Normal Stock", initPrice, initShares);
        this.initPrice = initPrice;
    }

    @Override
    public void fluctuate() {
        Random rand = new Random();
        double randValue = rand.nextGaussian();
        if (this.price < 5) {
            this.adjustPrice(1);
        } else {
            this.adjustPrice((float) (randValue * this.initPrice / 20.0));
        }
    }
}
