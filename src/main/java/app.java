package main.java;

import main.java.agent.IAgent;
import main.java.agent.RandomAgent;
import main.java.controller.TradingCenter;
import main.java.market.IStock;
import main.java.market.IStockMarket;
import main.java.market.NaiveMarket;
import main.java.market.PriceAmountPair;

import java.util.HashMap;

public class app {

    public static void main(String[] args) {

        IAgent agent = new RandomAgent();
        IStockMarket market = new NaiveMarket();
        TradingCenter tradingCenter = new TradingCenter(market, agent);
        int time = 1;

        while (!agent.broke()) {

            System.out.println("the current time is: " + time);

            HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo = market.getMarketInfo();
            HashMap<IStock, PriceAmountPair<Float, Integer>> buyDecision = agent.buyDecision(marketInfo);
            float cost = agent.calculateSum(buyDecision);
            HashMap<IStock, PriceAmountPair<Float, Integer>> sellDecision = agent.sellDecision(marketInfo);
            float profit = agent.calculateSum(sellDecision);

            market.adjustMarket(buyDecision, sellDecision);
            agent.updatePortfolio(buyDecision, sellDecision);
            float portfolioWorth = agent.portfolioWorth(marketInfo);
            agent.updateMoney(portfolioWorth, cost, profit);
            agent.printInfo();

            time = time + 1;

        }

        System.out.println("Ooops bad move! you are broke");
        System.out.println("Try again next time :))");
    }

}
