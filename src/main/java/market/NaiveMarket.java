package main.java.market;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NaiveMarket extends CommonStockMarket implements IStockMarket {

    public NaiveMarket(List<IStock> stockList) {
        super(stockList);
    }

    @Override
    public void adjustMarket(HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision,
                             HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision) {
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : buyDecision.entrySet()) {
            IStock stock = entry.getKey();
            int amount = entry.getValue().amount();
            stock.adjustAvailableShares(-amount);
            stock.adjustPrice(0);
        }
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : sellDecision.entrySet()) {
            IStock stock = entry.getKey();
            int amount = entry.getValue().amount();
            stock.adjustAvailableShares(amount);
            stock.adjustPrice(0);
        }
        this.updateMarketInfo();
    }

    @Override
    public void fluctuate() {  //todo: this should call the fluctuate function within each stock
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : this.marketInfo.entrySet()) {
            IStock stock = entry.getKey();
            Random rand = new Random();
            stock.adjustPrice((rand.nextInt(3) - 1) * (stock.getPrice() / 100));
        }
        this.updateMarketInfo();
    }

}
