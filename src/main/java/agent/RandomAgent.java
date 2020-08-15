package main.java.agent;

import main.java.market.IStock;
import main.java.market.PriceAmountPair;

import java.util.HashMap;

public class RandomAgent extends CommonAgent implements IAgent {

    public RandomAgent() {
        super();
    }

    public RandomAgent(float capital) {
        super(capital);
    }

    @Override
    public HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo) {
        return null;
    }

    @Override
    public HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo) {
        return null;
    }
}
