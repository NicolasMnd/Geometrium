package util.blueprints;

import util.helpers.Printer;

public abstract class Algoritme {

    protected Printer printer = new Printer();
    private final String name;

    public Algoritme(String name) {
        this.name = name;
    }

    public Printer getPrinter() {
        return this.printer;
    }

    public final String getName() {
        return this.name;
    }

    public abstract void run();

}
