package main.java.market;

import java.util.HashMap;

public class NaiveMarket implements IStockMarket {
    @Override
    public HashMap<IStock, PriceAmountPair<Float, Integer>> getMarketInfo() {
        return null;
    }

    @Override
    public void adjustMarket(HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision,
                             HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision) {

    }
}
