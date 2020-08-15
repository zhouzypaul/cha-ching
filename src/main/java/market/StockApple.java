package main.java.market;

public class StockApple extends CommonStock implements IStock {

    public StockApple(float initPrice, int initShares) {
        super("Apple", initPrice, initShares);
    }

}
