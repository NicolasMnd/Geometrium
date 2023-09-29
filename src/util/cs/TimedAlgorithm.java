package util.cs;

import util.blueprints.Algoritme;

public abstract class TimedAlgorithm extends Algoritme {

    private final int seconds;

    public TimedAlgorithm(int seconds) {
        super("naam");
        this.seconds = seconds;
    }

    @Override
    public void run() {

    }

}
