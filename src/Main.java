import util.Terminal;
import test.TestManager;
import util.PlaneDrawer;
import util.helpers.Wrapper;

import javax.swing.*;

import static util.helpers.InputHandler.getInput;

public class Main {

    public void openPanel() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Plane");

        PlaneDrawer drawPanel = new PlaneDrawer();
        window.add(drawPanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        drawPanel.startThread();
    }

    public static void main(String[] args) {

        Main main = new Main();

        while(true) {

            System.out.println("Please enter an option to continue\n");
            System.out.println("   1) Run program");
            System.out.println("   2) Run program with visuals");
            System.out.println("   3) Run tests\n");

            Wrapper w = getInput();
            if (w.isInteger() && w.getInt() == 1) {
                Terminal t = new Terminal();
                t.run();
            } else if (w.isInteger() && w.getInt() == 2)
                main.openPanel();
            else if(w.isInteger() && w.getInt() == 3) {
                TestManager.runTests();
            }

        }

    }

}
