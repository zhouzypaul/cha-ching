package main.java.stock;

public class CycleStock extends CommonStock implements IStock {

    public CycleStock(float initPrice, int initShares) {
        super("Cycle stock", initPrice, initShares);
    }

    @Override
    public void fluctuate() {
        // TODO
    }
}
