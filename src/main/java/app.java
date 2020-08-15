package main.java;

import main.java.agent.IAgent;
import main.java.agent.RandomAgent;
import main.java.market.IStock;
import main.java.market.IStockMarket;
import main.java.market.NaiveMarket;
import main.java.market.StockApple;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class app {

    public static void main(String[] args) {

        IAgent agent = new RandomAgent();
        IStock apple = new StockApple(30, 100000);
        List<IStock> stockList = new LinkedList<>();
        stockList.add(apple);
        IStockMarket market = new NaiveMarket(stockList);
        int time = 1;

        while (!agent.broke()) {

            System.out.println("time: " + time);

            List<IStock> marketInfo = market.getMarketInfo();
            HashMap<IStock, Integer> buyDecision = agent.buyDecision(marketInfo);
            float cost = agent.calculateSum(buyDecision);
            HashMap<IStock, Integer> sellDecision = agent.sellDecision(marketInfo);
            float profit = agent.calculateSum(sellDecision);

            agent.updatePortfolio(buyDecision, sellDecision);
            float portfolioWorth = agent.portfolioWorth();
            agent.updateMoney(portfolioWorth, cost, profit);
            agent.printInfo();

            time = time + 1;
            market.adjustMarket(buyDecision, sellDecision);
            market.fluctuate();
            market.printInfo();

        }

        System.out.println("Ooops bad move! you are broke");
        System.out.println("Try again next time :))");
    }

}
