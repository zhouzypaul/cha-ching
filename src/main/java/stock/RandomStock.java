package main.java.stock;

import java.util.Random;

/**
 * the random stock fluctuates up and down randomly, with equal probability to go up or down
 */
public class RandomStock extends CommonStock implements IStock {

    public RandomStock(float initPrice, int initShares) {
        super("Random Stock", initPrice, initShares);
    }

    @Override
    public void fluctuate() {
        Random rand = new Random();
        if (this.price < 5) {
            this.adjustPrice(1);
        } else {
            this.adjustPrice((rand.nextInt(3) - 1) * (float) (30.0 / 100));
        }
    }
}
