package main.java.agent;

import java.util.Random;

public class RandomAgent extends CommonAgent implements IAgent {

    public RandomAgent() {
        super();
    }

    public RandomAgent(float capital) {
        super(capital);
    }

    @Override
    public void decision(float sharePrice, int sharesBought) {
        Random rand = new Random();
        if (rand.nextFloat() > 0.5) {
            super.updateBalance(sharePrice * sharesBought);
            //TODO: communicate with controller
        }
    }

}
