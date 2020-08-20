package main.java.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IStockMarket {

    public List<IStock> getMarketInfo();

    public HashMap<IStock, ArrayList<Float>> getPastInfo();

    public void updatePastInfo();

    public void adjustMarket(HashMap<IStock, Integer> buyDecision,
                             HashMap<IStock, Integer> sellDecision);

    public void fluctuate();

    public void printInfo();

    public void drawInfo();

}