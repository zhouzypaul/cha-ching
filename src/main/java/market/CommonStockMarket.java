package main.java.market;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommonStockMarket implements IStockMarket {

    protected HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo;

    public CommonStockMarket(List<IStock> stockList) {
        this.marketInfo = new HashMap<>();
        for (IStock stock : stockList) {
            this.marketInfo.put(stock, new PriceAmountPair<>(stock.getPrice(), stock.getAvailableShares()));
        }
    }

    @Override
    public HashMap<IStock, PriceAmountPair<Float, Integer>> getMarketInfo() {
        return this.marketInfo;
    }

    protected void updateMarketInfo() {
        this.marketInfo.replaceAll((k, v) -> new PriceAmountPair<>(k.getPrice(), k.getAvailableShares()));
    }

    @Override
    public void printInfo() {
        System.out.println("the market condition is: ");
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : this.marketInfo.entrySet()) {
            IStock stock = entry.getKey();
            int availableAmount = entry.getValue().amount();
            float price = entry.getValue().price();
            System.out.println("market stock " + stock.getName() + " $" + price + " for " + availableAmount);
        }
    }
}
