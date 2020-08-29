package main.java.agent;

import main.java.stock.IStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the SOAgent uses the stochastic oscillator to decide on buying and selling
 * when the K line is above 80, this is overbought, consider selling
 * when the K line is below 20, this is oversold, consider buying
 */
public class SOAgent extends CommonAgent implements IAgent {

    private final int kLength;
    private final int dLength;

    public SOAgent() {
        super();
        this.kLength = 14;
        this.dLength = 3;
    }

    /**
     * %K = 100 * (CP - L14)/(H14 - L14)
     */
    private float getK(ArrayList<Float> ls) {
        int len = Math.min(this.kLength, ls.size());
        float max = ls.get(0);
        float min = ls.get(0);
        for (int i = 0; i < len; i++) {
            if (ls.get(i) > max) {
                max = ls.get(i);
            }
            if (ls.get(i) < min) {
                min = ls.get(i);
            }
        }
        return 100 * (ls.get(0) - min) / (max - min);
    }

    private float getD(ArrayList<Float> ls) {
        // todo
        return 0;
    }

    @Override
    public HashMap<IStock, Integer> buyDecision(List<IStock> marketInfo, HashMap<IStock, ArrayList<Float>> pastInfo) {
        HashMap<IStock, Integer> buy = new HashMap<>();
        for (IStock stock : marketInfo) {
            if (pastInfo.containsKey(stock)) {
                float stochastic = this.getK(pastInfo.get(stock));
                if (stochastic < 20) {
                    int buyAmount = Math.min(stock.getAvailableShares(), (int) (this.balance * 0.3 / stock.getPrice()));
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
                float stochastic = this.getK(pastInfo.get(stock));
                if (stochastic > 80) {
                    int sellAmount = (int) (0.5 * amountPossess);
                    sell.put(stock, sellAmount);
                }
            }
        }
        return sell;
    }

    @Override
    public String toString() {
        return "Stochastic Oscillator Agent";
    }
}
