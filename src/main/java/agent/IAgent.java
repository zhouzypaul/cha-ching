package main.java.agent;

import main.java.market.IStock;
import main.java.market.PriceAmountPair;

import java.util.HashMap;

public interface IAgent {

    public float getBalance();

    public HashMap<IStock, PriceAmountPair<Float, Integer>> getPortfolio();

    /**
     * determine whether the agent is broke or not
     *
     * @return true if the agent has no balance nor any portfolio, false otherwise
     */
    public boolean broke();

    /**
     * update the agent balance once it makes a purchase
     */
    public void updateBalance(float amountSpent);

    public void updatePortfolio(IStock stock, float amount);

    public void printPortfolio();

    public void decision(float sharePrice, int sharesBought); //TODO: change the output type
}