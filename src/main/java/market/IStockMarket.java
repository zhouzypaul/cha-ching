package main.java.market;

import main.java.stock.IStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IStockMarket {

    public int getTime();

    public List<IStock> getMarketInfo();

    public HashMap<IStock, ArrayList<Float>> getPastInfo();

    public float getAveragePrice();

    public void updatePastInfo();

    public void adjustMarket(HashMap<IStock, Integer> buyDecision,
                             HashMap<IStock, Integer> sellDecision);

    public void fluctuate();

    public void printInfo();

    public void drawInfo();

}