package main.java.controller;

import main.java.agent.IAgent;
import main.java.market.IStockMarket;


public class TradingCenter {

    private IStockMarket market;
    private IAgent agent;

    public TradingCenter(IStockMarket market, IAgent agent) {
        this.market = market;
        this.agent = agent;
    }

}
