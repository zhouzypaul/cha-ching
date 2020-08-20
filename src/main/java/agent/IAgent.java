package main.java.agent;

import main.java.market.IStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IAgent {

    public float getBalance();

    public float getNetWorth();

    public HashMap<IStock, Integer> getPortfolio();

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
    public void updatePortfolio(HashMap<IStock, Integer> buyDecision,
                                HashMap<IStock, Integer> sellDecision);

    public float calculateSum(HashMap<IStock, Integer> map);

    /**
     * calculate how much the portfolio is worth, money wise
     *
     * @return a float of the total value of the portfolio
     */
    public float portfolioWorth();

    public void printInfo();

    public HashMap<IStock, Integer> buyDecision(List<IStock> marketInfo,
                                                HashMap<IStock, ArrayList<Float>> pastInfo);

    public HashMap<IStock, Integer> sellDecision(List<IStock> marketInfo,
                                                 HashMap<IStock, ArrayList<Float>> pastInfo);
}