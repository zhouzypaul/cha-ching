package main.java.stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * real google stock data from 2015 - 2020 August
 */
public class GoogleStock extends CommonStock implements IStock {

    private ArrayList<Float> historyData;
    private int pointer;

    public GoogleStock(float initPrice, int initShares) {
        super("Google", initPrice, initShares);
        this.historyData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/data/GOOG.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                float value = Float.parseFloat(line);
                this.historyData.add(value);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.pointer = 0;
    }

    private void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("../data/GOOG.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                float value = Float.parseFloat(line);
                this.historyData.add(value);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fluctuate() {
        this.price = this.historyData.get(this.pointer);
        this.pointer++;
    }
}
