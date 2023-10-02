package util.blueprints;

import util.cs.OperationTime;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmeTester {

    private OperationTime timer;
    private List<Integer> times;
    private List<Integer> sizes;
    private final String name;

    public AlgoritmeTester(String name) {
        this.timer = new OperationTime();
        this.times = new ArrayList<>();
        this.sizes = new ArrayList<>();
        this.name = name;
    }

    public final void addEntry(int size, int milliseconds) {
        this.times.add(milliseconds);
        this.sizes.add(size);
    }

    public final void results() {
        System.out.println("Algoritme: " + name);
        System.out.println("  Size          Time");
        for(int i = 0; i < sizes.size(); i++) {
            System.out.println(times.get(i));
            //System.out.println("  " + sizes.get(i) + getSpace(sizes.get(i)) + times.get(i));
        }
    }

    private String getSpace(int key) {
        try {
            String keyString = Integer.toString(key);
            int length = keyString.length();
            int space = 14 - length;
            String spaceString = "";
            for(int i = 0; i < space; i++) {
                spaceString += " ";
            }
            return spaceString;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public final OperationTime getTimeOperator() {
        return this.timer;
    }

    public void test(Algoritme algoritme, int iterations, int size) {

        int count = 0;

        // We iterate n times
        while(count < iterations) {

            timer.reset();
            timer.start();

            algoritme.run();

            timer.stop();

            this.addEntry(size, timer.getMilliseconds());

            count++;

        }

    }

}
