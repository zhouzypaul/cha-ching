package main.java.market;

import java.util.List;

public abstract class CommonStockMarket implements IStockMarket {

    protected List<IStock> marketInfo;

    public CommonStockMarket(List<IStock> stockList) {
        this.marketInfo = stockList;
    }

    @Override
    public List<IStock> getMarketInfo() {
        return this.marketInfo;
    }

//    protected void updateMarketInfo() {
//        this.marketInfo.replaceAll((k, v) -> new PriceAmountPair<>(k.getPrice(), k.getAvailableShares()));
//    }

    @Override
    public void printInfo() {
        System.out.println("the market condition is: ");
        for (IStock stock : this.marketInfo) {
            System.out.println("market stock " + stock.getName() + " $" + stock.getPrice() + " for " + stock.getAvailableShares());
        }
    }
}
