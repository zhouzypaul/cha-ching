package test;

import main.java.market.NaiveMarket;
import main.java.stock.IStock;
import main.java.stock.RandomStock;

import java.util.LinkedList;
import java.util.List;

public class TestDraw {

    public static void main(String[] args) {
        IStock apple = new RandomStock(30, 100000);
        List<IStock> stockList = new LinkedList<>();
        stockList.add(apple);
        NaiveMarket market = new NaiveMarket(stockList, 10);

        market.drawInfoPy();
    }

}
