package test;

import main.java.agent.IAgent;
import main.java.agent.RandomAgent;

public class TestRandomAgent {

    public static void main(String[] args) {
        IAgent agent = new RandomAgent();
        agent.printInfo();
        System.out.println(agent.broke());
    }

}
