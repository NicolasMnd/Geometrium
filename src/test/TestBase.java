package test;

import util.helpers.Printer;
import static util.helpers.Printer.*;

public abstract class TestBase {

    private final String name;
    protected int tests = 0;
    protected int fails = 0;
    private final Printer printer = new Printer();


    public TestBase(String s) {
        this.name = s;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Gives an evaluation of the functionality of this class.
     */
    public final void evaluate() {
        printer.print("\n" + Printer.GREEN_BOLD + "Test of " + getName() + " passed " + (tests-fails) + " of " + tests + " tests" + Printer.ANSI_RESET);
    }

    /**
     * Runs all tests for the implementation
     */
    public void test() {
        printer.print("Tests for ", getName());

        evaluate();
    }

    /**
     * Tests if values match & prints message according to result
     */
    public final void testValue(String test, int a, int b) {
        tests++;
        if(a != b) {
            fails++;
            fail(test, Integer.toString(a), Integer.toString(b));
        }
        else pass(test, Integer.toString(a), Integer.toString(b));
    }

    /**
     * Tests if values match & prints message according to result
     */
    public final void testValue(String test, boolean a, boolean b) {
        tests++;
        if(a != b) {
            fail(test, Boolean.toString(a), Boolean.toString(b));
            fails++;
        }
        else pass(test, Boolean.toString(a), Boolean.toString(b));
    }

    /**
     * Tests if values match & prints message according to result
     */
    public final void testValue(int[] arr1, int[] arr2) {
        tests++;
        if(arr1.length != arr2.length) printer.print("Test invalid: " + printer.arrString(arr1) + " - " + printer.arrString(arr2) + ". Array lengths do not match.");

        boolean equal = true;
        for(int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) equal = false;
        }
        if(equal) match(printer.arrString(arr1), printer.arrString(arr2));
        else {
            fails++;
            nonmatch(printer.arrString(arr1), printer.arrString(arr2));
        }
    }

    public void match(String value1, String value2) {
        printer.print(passPrint(), value1, " and ", value2, " match.");
    }

    public void nonmatch(String value1, String value2) {
        //print(failPrint() + value1 + " and " + value2 + " don't match.");
        printer.print(failPrint(), value1, " and ", value2, " don't match.");
    }

    public void fail(String values, String expected, String calculated) {
        //print(failPrint() + values + " -> expected: " + expected + " ; calculated: " + calculated);
        printer.print(failPrint(), values, " -> expected: ", expected, " ; calculated: ", calculated);
    }

    public void pass(String values, String expected, String calculated) {
        //print(passPrint() + values + " -> expected: " + expected + " ; calculated: " + calculated);
        printer.print(passPrint(), values, " -> expected: ", expected, " ; calculated: ", calculated);
    }

    public String failPrint() {
        return printer.build("Test ", red("failed"), ": ");
        //return "Test " + red("failed") + ": ";
    }

    public String passPrint() {
        return printer.build("Test ", green("passed"), ": ");
        //return "Test " + green("passed") + ": ";
    }

    /**
     * Makes given string red.
     */
    public String red(String s) {
        return Printer.RED_BOLD_BRIGHT + s + Printer.ANSI_RESET;
    }

    /**
     * Makes given string green
     */
    public String green(String s) {
        return Printer.GREEN_BOLD_BRIGHT + s + Printer.ANSI_RESET;
    }

}
