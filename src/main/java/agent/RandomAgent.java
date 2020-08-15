package main.java.agent;

import main.java.market.IStock;
import main.java.market.PriceAmountPair;

import java.util.HashMap;
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
    public HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo) {
        HashMap<IStock, PriceAmountPair<Float, Integer>> buy = new HashMap<>();
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : marketInfo.entrySet()) {
            IStock stock = entry.getKey();
            Float sharePrice = entry.getValue().price();
            int sharesAvailable = entry.getValue().amount();
            if (this.balance > sharePrice) {
                Random rand = new Random();
                if (rand.nextDouble() > 0.5) {
                    int buyAmount = rand.nextInt(Math.min(sharesAvailable / 100, (int) (this.balance / sharePrice / 10)));
                    buy.put(stock, new PriceAmountPair<>(sharePrice, buyAmount));
                }
            }
        }
        return buy;
    }

    @Override
    public HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo) {
        HashMap<IStock, PriceAmountPair<Float, Integer>> sell = new HashMap<>();
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : this.portfolio.entrySet()) {
            IStock stock = entry.getKey();
            Float sharePrice = entry.getValue().price();
            int sharesAvailable = entry.getValue().amount();
            Random rand = new Random();
            if (rand.nextDouble() > 0.5) {
                int sellAmount = rand.nextInt(sharesAvailable);
                sell.put(stock, new PriceAmountPair<>(sharePrice, sellAmount));
            }
        }
        return sell;
    }
}
