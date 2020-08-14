package main.java.agent;

import main.java.market.IStock;
import main.java.market.PriceAmountPair;

import java.util.HashMap;

public abstract class CommonAgent implements IAgent {

    protected float capital;
    protected float balance;
    protected HashMap<IStock, PriceAmountPair<Float, Integer>> portfolio;

    public CommonAgent() {
        this.capital = 10000;
        this.balance = this.capital;
        this.portfolio = new HashMap<IStock, PriceAmountPair<Float, Integer>>();
    }

    public CommonAgent(float capital) {
        this.capital = capital;
        this.balance = this.capital;
    }

    @Override
    public float getBalance() {
        return this.balance;
    }

    @Override
    public HashMap<IStock, PriceAmountPair<Float, Integer>> getPortfolio() {
        return this.portfolio;
    }

    @Override
    public boolean broke() {
        return false; //TODO
    }

    @Override
    public void updateBalance(float amountSpent) {
        this.balance = this.balance - amountSpent;
    }

    @Override
    public void updatePortfolio(IStock stockName, float amount) {
        //TODO
    }

    @Override
    public void printPortfolio() {
        //TODO
    }
}
