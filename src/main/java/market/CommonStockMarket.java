package main.java.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public abstract class CommonStockMarket implements IStockMarket {

    protected List<IStock> marketInfo;

    public CommonStockMarket(List<IStock> stockList) {
        this.marketInfo = stockList;
    }

    @Override
    public List<IStock> getMarketInfo() {
        return this.marketInfo;
    }

//    protected void updateMarketInfo() {
//        this.marketInfo.replaceAll((k, v) -> new PriceAmountPair<>(k.getPrice(), k.getAvailableShares()));
//    }

    @Override
    public void printInfo() {
        System.out.println("the market condition is: ");
        for (IStock stock : this.marketInfo) {
            System.out.println("market stock " + stock.getName() + " $" + stock.getPrice() + " for " + stock.getAvailableShares());
        }
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
