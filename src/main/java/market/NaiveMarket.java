package main.java.market;

import main.java.stock.IStock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveMarket extends CommonStockMarket implements IStockMarket {

    public NaiveMarket(List<IStock> stockList, int historyLen) {
        super(stockList, historyLen);
    }

    @Override
    public void adjustMarket(HashMap<IStock, Integer> buyDecision,
                             HashMap<IStock, Integer> sellDecision) {
        for (Map.Entry<IStock, Integer> entry : buyDecision.entrySet()) {
            IStock stock = entry.getKey();
            int amount = entry.getValue();
            stock.adjustAvailableShares(-amount);
            stock.adjustPrice(0);
        }
        for (Map.Entry<IStock, Integer> entry : sellDecision.entrySet()) {
            IStock stock = entry.getKey();
            int amount = entry.getValue();
            stock.adjustAvailableShares(amount);
            stock.adjustPrice(0);
        }
    }

    @Override
    public void fluctuate() {
        for (IStock stock : this.marketInfo) {
            stock.fluctuate();
        }
        this.time += 1;
    }

}
