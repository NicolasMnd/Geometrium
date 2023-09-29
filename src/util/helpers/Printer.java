package util.helpers;

import java.util.List;

public class Printer {

    /**
     * Source: https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println/5762502#5762502
     */
    //Normal
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK = "\033[0;90m";  // BLACK
    public static final String RED = "\033[0;91m";    // RED
    public static final String GREEN = "\033[0;92m";  // GREEN
    public static final String YELLOW = "\033[0;93m"; // YELLOW
    public static final String BLUE = "\033[0;94m";   // BLUE
    public static final String PURPLE = "\033[0;95m"; // PURPLE
    public static final String CYAN = "\033[0;96m";   // CYAN
    public static final String WHITE = "\033[0;97m";
    //Darker
    public static final String BLACKD = "\033[0;30m";   // BLACK
    public static final String REDD = "\033[0;31m";     // RED
    public static final String GREEND = "\033[0;32m";   // GREEN
    public static final String YELLOWD = "\033[0;33m";  // YELLOW
    public static final String BLUED = "\033[0;34m";    // BLUE
    public static final String PURPLED = "\033[0;35m";  // PURPLE
    public static final String CYAND = "\033[0;36m";    // CYAN
    public static final String WHITED = "\033[0;37m";   // WHITE
    //Bold
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
    //Bold light
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    private String origin;
    private boolean printOrigin = false;
    private boolean allowPrints = true;

    public Printer() {
        origin = "unnamed origin";
    }

    public Printer(String origin) {
        this.origin = origin;
        printOrigin = true;
    }

    public void allowPrints() {
        this.allowPrints = true;
    }

    public void stopPrints() {
        this.allowPrints = false;
    }

    public static void prints(String s) {
        System.out.println(s);
    }

    public void print(String s) {
        if(allowPrints)
            System.out.println(s);
    }

    public void printo(String s) {
        print(origin + " " + s);
    }

    public void printArr(int[] arr) {
        print(arrString(arr));
    }

    public String arrString(int[] arr) {
        StringBuilder b = new StringBuilder();
        b.append("[");
        for(int i = 0; i < arr.length; i++) {
            b.append(arr[i]);
            if(i < arr.length-1) b.append(", ");
        }
        b.append("]");
        return b.toString();
    }

    public String floatString(float[] arr) {
        StringBuilder b = new StringBuilder();
        b.append("[");
        for(int i = 0; i < arr.length; i++) {
            b.append(Float.toString(arr[i]));
            if(i < arr.length-1) b.append(", ");
        }
        b.append("]");
        return b.toString();
    }

    public void debug(String s) {
        System.out.println("[" + origin + "] " + s);
    }

    /**
     * Possible new way to print out our tests
     */
    public void print(String... s) {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < s.length; i++) {
            b.append(s[i]);
        }
        print(b.toString());
    }

    public String build(String... s) {
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < s.length; i++) {
            b.append(s[i]);
        }
        return b.toString();
    }

    public String doubleArrString(int[][] arr) {
        StringBuilder b = new StringBuilder();
        b.append("{");
        for(int i = 0; i < arr.length; i++) {
            b.append("[");
            for(int j = 0; j < arr[i].length; j++) {
                b.append(arr[i][j]);
                if (j < arr[i].length - 1) b.append(", ");
            }
            if(i < arr.length-1) b.append("], ");
            else b.append("]");
        }
        b.append("}");
        return b.toString();
    }

    public String listString(List<?> list) {
        StringBuilder b = new StringBuilder();
        b.append("[");
        for(int i = 0; i < list.size(); i++) {
            b.append(list.get(i));
            if(i < list.size()-1) b.append(", ");
        }
        b.append("]");
        return b.toString();
    }

}
