package main.java.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CommonStockMarket implements IStockMarket {

    protected List<IStock> marketInfo;
    protected HashMap<IStock, ArrayList<Float>> pastInfo;
    protected int historyLen;
    protected int time;

    public CommonStockMarket(List<IStock> stockList, int historyLen) {
        this.marketInfo = stockList;
        this.pastInfo = new HashMap<>();
        this.historyLen = historyLen;
        this.time = 1;
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public List<IStock> getMarketInfo() {
        return this.marketInfo;
    }

    @Override
    public HashMap<IStock, ArrayList<Float>> getPastInfo() {
        return this.pastInfo;
    }

    @Override
    public float getAveragePrice() {
        float average = 0;
        for (IStock stock : this.marketInfo) {
            average += stock.getPrice() / this.marketInfo.size();
        }
        return average;
    }

    @Override
    public void updatePastInfo() {
        for (IStock stock : this.marketInfo) {
            if (this.pastInfo.containsKey(stock)) {
                if (this.pastInfo.get(stock).size() >= this.historyLen) {
                    this.pastInfo.get(stock).remove(this.historyLen - 1);
                }
                this.pastInfo.get(stock).add(0, stock.getPrice());
            } else {
                ArrayList<Float> newList = new ArrayList<>();
                newList.add(stock.getPrice());
                this.pastInfo.put(stock, newList);
            }
        }
    }

    @Override
    public void printInfo() {
//        System.out.println();
        System.out.println("-----------");
        System.out.println("the market condition is: ");
        for (IStock stock : this.marketInfo) {
            System.out.println("market stock " + stock.getName() + " $" + stock.getPrice() + " for " + stock.getAvailableShares());
        }
        System.out.println("----------------------------");
        System.out.println();
    }

    @Override
    public void drawInfo() {

    }

    public void drawInfoPy() {
        try {
            String s = null;
            // run the python draw command
            Process p = Runtime.getRuntime().exec("python src/main/python/draw.py");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
//            System.out.println("error is: " + stdError.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
