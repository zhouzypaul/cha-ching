package main.java;

import main.java.agent.AutoAgent;
import main.java.agent.IAgent;
import main.java.graph.XYGraph;
import main.java.market.IStock;
import main.java.market.IStockMarket;
import main.java.market.NaiveMarket;
import main.java.market.StockApple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class app {

    public static void main(String[] args) {

        IAgent agent = new AutoAgent(5);
        IStock apple = new StockApple(30, 100);
        List<IStock> stockList = new LinkedList<>();
        stockList.add(apple);
        IStockMarket market = new NaiveMarket(stockList, 10);
        int time = 1;
        int timeLimit = 1000000;
        boolean debug = false;
        ArrayList<Float> netW = new ArrayList<>();
        ArrayList<Float> portfolioW = new ArrayList<>();
        ArrayList<Float> stockW = new ArrayList<>();

        while (time <= timeLimit && !agent.broke()) {

            System.out.println("time: " + time);

            List<IStock> marketInfo = market.getMarketInfo();
            HashMap<IStock, ArrayList<Float>> pastInfo = market.getPastInfo();
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

            agent.updatePortfolio(buyDecision, sellDecision);
            float portfolioWorth = agent.portfolioWorth();
            agent.updateMoney(portfolioWorth, cost, profit);
            agent.printInfo();
            netW.add(agent.getNetWorth());
            portfolioW.add(portfolioWorth);
            stockW.add(marketInfo.get(0).getPrice() * 300);

            time = time + 1;
            market.updatePastInfo();
            market.adjustMarket(buyDecision, sellDecision);
            market.fluctuate();
//            market.printInfo();

        }

//        System.out.println("Ooops bad move! you are broke");
//        System.out.println("Try again next time :))");
        XYGraph graph = new XYGraph("agent status", "money", netW, portfolioW, stockW);
        graph.pack();
        graph.setVisible(true);
    }

}
