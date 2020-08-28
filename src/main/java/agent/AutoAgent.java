package main.java.agent;

import main.java.stock.IStock;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the auto agent looks at a sequence of history from the stock market
 * if the stock seems to be going down, it buys
 * if the stock seems to be going up, it sells
 * the stock market trend is analyzed using linear regression on the past few data points
 */
public class AutoAgent extends CommonAgent implements IAgent {

    private final int historyLen;

    public AutoAgent(int historyLen) {
        super();
        this.historyLen = historyLen;
    }

    public AutoAgent(float capital, int historyLen) {
        super(capital);
        this.historyLen = historyLen;
    }

    @Override
    public HashMap<IStock, Integer> buyDecision(List<IStock> marketInfo, HashMap<IStock, ArrayList<Float>> pastInfo) {
        HashMap<IStock, Integer> buy = new HashMap<>();
        for (IStock stock : marketInfo) {
            if (pastInfo.containsKey(stock)) { // don't buy stock on the first day
                int hist = Math.min(pastInfo.get(stock).size(), this.historyLen);
                SimpleRegression reg = new SimpleRegression();
                for (int i = 0; i < hist; i++) {
                    reg.addData(hist - i, pastInfo.get(stock).get(i));
                }
                reg.addData(hist, stock.getPrice());
                double slope = reg.getSlope();
                if (slope < 0) {
                    int buyAmount = Math.min(stock.getAvailableShares(), (int) (this.balance * 0.1 / stock.getPrice()));
                    buy.put(stock, buyAmount);
                }
            }
        }
        return buy;
    }

    @Override
    public HashMap<IStock, Integer> sellDecision(List<IStock> marketInfo, HashMap<IStock, ArrayList<Float>> pastInfo) {
        HashMap<IStock, Integer> sell = new HashMap<>();
        for (Map.Entry<IStock, Integer> entry : this.portfolio.entrySet()) {
            IStock stock = entry.getKey();
            int amountPossess = entry.getValue();
            if (pastInfo.containsKey(stock)) {
                int hist = Math.min(pastInfo.get(stock).size(), this.historyLen);
                SimpleRegression reg = new SimpleRegression();
                for (int i = 0; i < hist; i++) {
                    reg.addData(hist - i, pastInfo.get(stock).get(i));
                }
                reg.addData(hist, stock.getPrice());
                double slope = reg.getSlope();
                if (slope > 0) {
                    int sellAmount = (int) (0.5 * amountPossess);
                    sell.put(stock, sellAmount);
                }
            }
        }
        return sell;
    }

    @Override
    public String toString() {
        return "Auto Agent";
    }

}
