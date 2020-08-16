package test;

import main.java.market.IStock;
import main.java.market.NaiveMarket;
import main.java.market.StockApple;

import java.util.LinkedList;
import java.util.List;

public class TestDraw {

    public static void main(String[] args) {
        IStock apple = new StockApple(30, 100000);
        List<IStock> stockList = new LinkedList<>();
        stockList.add(apple);
        NaiveMarket market = new NaiveMarket(stockList);

        market.drawInfoPy();
    }

}
