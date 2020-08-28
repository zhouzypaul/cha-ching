package main.java.agent;

import java.util.ArrayList;

/**
 * the EMA Agent uses the Exponential Moving Average indicator
 * EMA is a MA that has a exponential weight, that places a greater significance on most recent data
 */
public class EMAAgent extends AveragingAgent implements IAgent {

    private final int shortLen;
    private final int longLen;
    private final int smoothing = 2;
//    private float previousEMA = 0;

    public EMAAgent(int shortLen, int longLen) {
        super(shortLen, longLen);
        this.shortLen = shortLen;
        this.longLen = longLen;
    }

    public EMAAgent(float capital, int shortLen, int longLen) {
        super(capital, shortLen, longLen);
        this.shortLen = shortLen;
        this.longLen = longLen;
    }

    /**
     * EMA = Closing price x multiplier + EMA (previous day) x (1-multiplier)
     */
    private float getEMA(ArrayList<Float> ls, int length) {
        float ema = 0;
        for (int i = length - 1; i >= 0; i--) {
            float multiplier = (float) this.smoothing / (length - i);
            ema = ls.get(i) * multiplier + ema * (1 - multiplier);
        }
        return ema;
    }

    @Override
    protected float averageFunc(ArrayList<Float> ls, int length) {
        return this.getEMA(ls, length);
    }

    @Override
    public String toString() {
        return "Exponential Averaging Agent";
    }

}
