package util.helpers;

import java.util.Scanner;

public class InputHandler {

    private Scanner scanner;
    private final Printer printer;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
        this.printer = new Printer();
    }

    public Wrapper getInputSafely() {
        Object input;

        while (true) {
            try {
                input = scanner.nextLine();

                if(input instanceof Integer) {
                    return new Wrapper((Integer) input);
                } else if (input instanceof String) {
                    try {
                        return new Wrapper(Integer.parseInt((String) input));
                    } catch (NumberFormatException e) {
                        return new Wrapper((String) input);
                    }
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public static Wrapper getInput() {
        return (new InputHandler()).getInputSafely();
    }

}
