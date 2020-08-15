package main.java.market;

import java.util.HashMap;

public interface IStockMarket {

    public HashMap<IStock, PriceAmountPair<Float, Integer>> getMarketInfo();

    public void adjustMarket(HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision,
                             HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision);

    public void fluctuate();

    public void printInfo();

}