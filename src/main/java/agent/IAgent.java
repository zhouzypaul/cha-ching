package main.java.agent;

import main.java.market.IStock;
import main.java.market.PriceAmountPair;

import java.util.HashMap;

public interface IAgent {

    public float getBalance();

    public float getNetWorth();

    public HashMap<IStock, PriceAmountPair<Float, Integer>> getPortfolio();

    /**
     * determine whether the agent is broke or not
     *
     * @return true if the agent has no balance nor any portfolio, false otherwise
     */
    public boolean broke();

    public void updateMoney(float portfolioWorth, float cost, float profit);

    /**
     * update the holdings of the agent after buying and selling decisions
     *
     * @param buyDecision  a hashmap of how much stock the agent buys
     * @param sellDecision a hashmap of how much stock the agent sells
     */
    public void updatePortfolio(HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision,
                                HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision);

    public float calculateSum(HashMap<IStock, PriceAmountPair<Float, Integer>> map);

    /**
     * calculate how much the portfolio is worth, money wise
     *
     * @param marketInfo the price of each stock in the market in a hashmap
     * @return a float of the total value of the portfolio
     */
    public float portfolioWorth(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo);

    public void printInfo();

    public HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo);

    public HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo);
}