package test;

import java.util.HashMap;
import java.util.Map;

public class TestCommonAgent {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        System.out.println("start");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("hello");
        }
    }

}
