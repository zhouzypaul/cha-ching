package main.java;

import main.java.agent.IAgent;
import main.java.agent.RandomAgent;
import main.java.controller.TradingCenter;
import main.java.market.IStockMarket;
import main.java.market.NaiveMarket;

public class app {

    public static void main(String[] args) {

        IAgent randAgent = new RandomAgent();
        IStockMarket market = new NaiveMarket();
        TradingCenter tradingCenter = new TradingCenter(market, randAgent);
        int time = 1;

        while (!randAgent.broke()) {
            // TODO:

            // first day
            // marketInfo = market.getMarketInfo();
            // agentDecision = randAgent.decision(marketInfo);

            // second day
            // time += 1;
            // market.adjustMarket(agentDecision);
            // agent.updatePortfolio();
        }

        System.out.println("GAME OVER! you are broke");
        System.out.println("Try again next time :))");
    }

}
