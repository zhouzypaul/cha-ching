package main.java.agent;

import main.java.market.IStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the averaging agent looks at a long-term history average and short-term history average
 * if the short term average is above the long-term average, the stock is starting to rise, agent buys
 * if the short term average is below the long-term average, the stock is beginning to decline, agent sells
 * this is the 'moving average' technique used by traders
 */
public class AveragingAgent extends CommonAgent implements IAgent {

    private final int shortLen;
    private final int longLen;

    public AveragingAgent(int shortLen, int longLen) {
        super();
        this.shortLen = shortLen;
        this.longLen = longLen;
    }

    public AveragingAgent(float capital, int shortLen, int longLen) {
        super(capital);
        this.shortLen = shortLen;
        this.longLen = longLen;
    }

    public float getAverage(ArrayList<Float> ls, int length) {
        if (length > ls.size()) {
            throw new RuntimeException("tried getting an average longer than the list");
        }
        float avg = 0;
        for (int i = 0; i < length; i++) {
            avg += ls.get(i) / length;
        }
        return avg;
    }

    @Override
    public HashMap<IStock, Integer> buyDecision(List<IStock> marketInfo, HashMap<IStock, ArrayList<Float>> pastInfo) {
        HashMap<IStock, Integer> buy = new HashMap<>();
        for (IStock stock : marketInfo) {
            if (pastInfo.containsKey(stock)) { // don't buy anything on first day
                int longHist = Math.min(this.longLen, pastInfo.get(stock).size());
                int shortHist = Math.min(this.shortLen, pastInfo.get(stock).size());
                float longAvg = getAverage(pastInfo.get(stock), longHist);
                float shortAvg = getAverage(pastInfo.get(stock), shortHist);
                if (shortAvg > longAvg) {
                    float percent = (shortAvg - longAvg) / longAvg;
                    int buyAmount = Math.min(stock.getAvailableShares(), (int) (this.balance * percent / stock.getPrice()));
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
            int amountPosses = entry.getValue();
            if (pastInfo.containsKey(stock)) {
                int longHist = Math.min(this.longLen, pastInfo.size());
                int shortHist = Math.min(this.shortLen, pastInfo.size());
                float longAvg = getAverage(pastInfo.get(stock), longHist);
                float shortAvg = getAverage(pastInfo.get(stock), shortHist);
                if (shortAvg < longAvg) {
                    float percent = (longAvg - shortAvg) / shortAvg;
                    int sellAmount = (int) (percent * amountPosses);
                    sell.put(stock, sellAmount);
                }
            }
        }
        return sell;
    }

    @Override
    public String toString() {
        return "Averaging Agent";
    }
}
