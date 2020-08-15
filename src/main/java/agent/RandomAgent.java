package main.java.agent;

import main.java.market.IStock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomAgent extends CommonAgent implements IAgent {

    public RandomAgent() {
        super();
    }

    public RandomAgent(float capital) {
        super(capital);
    }

    @Override
    public HashMap<IStock, Integer> buyDecision(List<IStock> marketInfo) {
        HashMap<IStock, Integer> buy = new HashMap<>();
        for (IStock stock : marketInfo) {
            float sharePrice = stock.getPrice();
            int sharesAvailable = stock.getAvailableShares();
            if (this.balance > sharePrice) {
                Random rand = new Random();
                if (rand.nextDouble() > 0.5) {
                    int buyAmount = rand.nextInt(Math.min(sharesAvailable / 100, (int) (this.balance / sharePrice / 10)));
                    buy.put(stock, buyAmount);
                }
            }
        }
        return buy;
    }

    @Override
    public HashMap<IStock, Integer> sellDecision(List<IStock> marketInfo) {
        HashMap<IStock, Integer> sell = new HashMap<>();
        for (Map.Entry<IStock, Integer> entry : this.portfolio.entrySet()) {
            IStock stock = entry.getKey();
            int sharesPossess = entry.getValue();
            Random rand = new Random();
            if (rand.nextDouble() > 0.5) {
                int sellAmount = rand.nextInt(sharesPossess);
                sell.put(stock, sellAmount);
            }
        }
        return sell;
    }

}
