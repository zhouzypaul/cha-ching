package main.java.agent;

import main.java.market.IStock;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoAgent extends CommonAgent implements IAgent {

    public AutoAgent(int historyLen) {
        super(historyLen);
    }

    public AutoAgent(float capital, int historyLen) {
        super(capital, historyLen);
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
