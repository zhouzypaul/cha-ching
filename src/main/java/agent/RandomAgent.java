package main.java.agent;

import main.java.stock.IStock;

import java.util.*;

/**
 * the random agent buys and sells randomly
 */
public class RandomAgent extends CommonAgent implements IAgent {

    public RandomAgent() {
        super();
    }

    public RandomAgent(float capital) {
        super(capital);
    }

    @Override
    public HashMap<IStock, Integer> buyDecision(List<IStock> marketInfo, HashMap<IStock, ArrayList<Float>> pastInfo) {
        HashMap<IStock, Integer> buy = new HashMap<>();
        for (IStock stock : marketInfo) {
            float sharePrice = stock.getPrice();
            int sharesAvailable = stock.getAvailableShares();
            if (this.balance > sharePrice) {
                Random rand = new Random();
                if (rand.nextDouble() > 0.5) {
                    int buyAmount = rand.nextInt(Math.min(sharesAvailable / 10 + 1, (int) (this.balance / sharePrice / 10 + 1)));
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
            int sharesPossess = entry.getValue();
            Random rand = new Random();
            if (rand.nextDouble() > 0.5 && sharesPossess > 0) {
                int sellAmount = rand.nextInt(sharesPossess);
                sell.put(stock, sellAmount);
            }
        }
        return sell;
    }

    @Override
    public String toString() {
        return "Random Agent";
    }

}
