package main.java;

import main.java.agent.AutoAgent;
import main.java.agent.AveragingAgent;
import main.java.agent.IAgent;
import main.java.agent.RandomAgent;
import main.java.graph.XYGraph;
import main.java.market.IStock;
import main.java.market.IStockMarket;
import main.java.market.NaiveMarket;
import main.java.market.RandomStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class app {

    public static void main(String[] args) {

        IAgent autoAgent = new AutoAgent(5);
        IAgent randAgent = new RandomAgent();
        IAgent avgAgent = new AveragingAgent(5, 20);
        ArrayList<IAgent> agentList = new ArrayList<>();
        agentList.add(autoAgent);
        agentList.add(randAgent);
        agentList.add(avgAgent);
        IStock randStock = new RandomStock(10, 1000);
        IStock randStock2 = new RandomStock(15, 1000);
        IStock randStock3 = new RandomStock(20, 1000);
        List<IStock> stockList = new LinkedList<>();
        stockList.add(randStock);
        stockList.add(randStock2);
        stockList.add(randStock3);
        IStockMarket market = new NaiveMarket(stockList, 10);

        int timeLimit = 10000;
        boolean debug = false;

        HashMap<IAgent, ArrayList<Float>> netW = new HashMap<>();
        HashMap<IAgent, ArrayList<Float>> portfolioW = new HashMap<>();
        HashMap<IAgent, ArrayList<Float>> stockW = new HashMap<>();
        for (IAgent agent : agentList) {
            netW.put(agent, new ArrayList<>());
            portfolioW.put(agent, new ArrayList<>());
            stockW.put(agent, new ArrayList<>());
        }

        while (market.getTime() <= timeLimit) {

            System.out.println("time: " + market.getTime());
            List<IStock> marketInfo = market.getMarketInfo();
            HashMap<IStock, ArrayList<Float>> pastInfo = market.getPastInfo();
            for (IAgent agent : agentList) {
                // if broke
                if (agent.broke()) {
                    break;
                }

                // agent make decisions
                HashMap<IStock, Integer> buyDecision = agent.buyDecision(marketInfo, pastInfo);
                if (debug) {
                    System.out.println("the buy decision is: ");
                    System.out.println(buyDecision.toString());
                }
                float cost = agent.calculateSum(buyDecision);
                HashMap<IStock, Integer> sellDecision = agent.sellDecision(marketInfo, pastInfo);
                if (debug) {
                    System.out.println("the sell decision is: ");
                    System.out.println(sellDecision);
                }
                float profit = agent.calculateSum(sellDecision);

                // agent update
                agent.updatePortfolio(buyDecision, sellDecision);
                float portfolioWorth = agent.portfolioWorth();
                agent.updateMoney(portfolioWorth, cost, profit);
                agent.printInfo();

                // graph
                netW.get(agent).add(agent.getNetWorth());
                portfolioW.get(agent).add(portfolioWorth);
                stockW.get(agent).add(market.getAveragePrice() * 2000);

                // market update
                market.adjustMarket(buyDecision, sellDecision);
            }

            market.updatePastInfo();
            market.fluctuate();
//            market.printInfo();

        }

//        System.out.println("Ooops bad move! you are broke");
//        System.out.println("Try again next time :))");
        for (IAgent agent : agentList) {
            XYGraph graph = new XYGraph(agent.toString(), "status", netW.get(agent), portfolioW.get(agent), stockW.get(agent));
            graph.pack();
            graph.setVisible(true);
        }

    }

}
