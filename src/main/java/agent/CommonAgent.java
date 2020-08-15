package main.java.agent;

import main.java.market.IStock;
import main.java.market.PriceAmountPair;

import java.util.HashMap;
import java.util.Map;

public abstract class CommonAgent implements IAgent {

    protected float capital;
    protected float balance;
    protected float netWorth;
    protected HashMap<IStock, PriceAmountPair<Float, Integer>> portfolio;

    public CommonAgent() {
        this.capital = 10000;
        this.balance = this.capital;
        this.portfolio = new HashMap<>();
        this.netWorth = this.balance;
    }

    public CommonAgent(float capital) {
        this.capital = capital;
        this.balance = this.capital;
        this.portfolio = new HashMap<>();
        this.netWorth = this.balance;
    }

    @Override
    public float getBalance() {
        return this.balance;
    }

    @Override
    public float getNetWorth() {
        return this.netWorth;
    }

    @Override
    public HashMap<IStock, PriceAmountPair<Float, Integer>> getPortfolio() {
        return this.portfolio;
    }

    @Override
    public boolean broke() {
        return this.balance <= 0 && this.portfolio.isEmpty();
    }

    @Override
    public void updatePortfolio(HashMap<IStock, PriceAmountPair<Float, Integer>> buy,
                                HashMap<IStock, PriceAmountPair<Float, Integer>> sell) {
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : buy.entrySet()) {
            IStock stock = entry.getKey();
            Integer shares = entry.getValue().amount();
            Float sharePrice = entry.getValue().price();
            if (this.portfolio.containsKey(stock)) {
                this.portfolio.put(stock, new PriceAmountPair<>(sharePrice, this.portfolio.get(stock).amount() + shares));
            } else {
                this.portfolio.put(stock, new PriceAmountPair<>(sharePrice, shares));
            }
        }
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : sell.entrySet()) {
            IStock stock = entry.getKey();
            Integer shares = entry.getValue().amount();
            Float sharePrice = entry.getValue().price();
            if (this.portfolio.containsKey(stock)) {
                if (this.portfolio.get(stock).amount() >= shares) {
                    this.portfolio.put(stock, new PriceAmountPair<>(sharePrice, this.portfolio.get(stock).amount() - shares));
                } else {
                    throw new RuntimeException("trying to sell stocks more than possessed");
                }
            } else {
                throw new RuntimeException("trying to sell stocks the agent doesn't possess");
            }
        }
    }

    @Override
    public float calculateSum(HashMap<IStock, PriceAmountPair<Float, Integer>> map) {
        float sum = 0;
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : map.entrySet()) {
            sum = sum + entry.getValue().totalValue();
        }
        return sum;
    }

    @Override
    public void updateMoney(float portfolioWorth, float cost, float profit) {
        this.balance = this.balance - cost + profit;
        this.netWorth = this.balance + portfolioWorth;
    }

    @Override
    public float portfolioWorth(HashMap<IStock, PriceAmountPair<Float, Integer>> marketInfo) {
        return this.calculateSum(this.portfolio);
    }
    
    public void printPortfolio() {
        System.out.println("The agent is in possession of the following stocks");
        for (Map.Entry<IStock, PriceAmountPair<Float, Integer>> entry : this.portfolio.entrySet()) {
            IStock stock = entry.getKey();
            int amount = entry.getValue().amount();
            float totalPrice = entry.getValue().totalValue();
            System.out.println(stock.getName() + ": " + "shares: " + amount + " total worth: " + totalPrice);
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Here's you current status: ");
        System.out.println("----------------------------");
        System.out.println("You balance is: " + this.getBalance());
        System.out.println("Your net worth is: " + this.getNetWorth());
        this.printPortfolio();
        System.out.println("----------------------------");
    }
}
