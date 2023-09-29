package convexhull;

import util.blueprints.AlgoritmeTester;
import util.blueprints.PaintInformation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Terminal {

    private List<PaintInformation> paintComponents = new ArrayList<>();

    public Terminal() {

    }

    public void addComponents(List<PaintInformation> components) {
        this.paintComponents.addAll(components);
    }

    /**
     * Methode wordt opgeroepen in de JPanel constructor.
     */
    public void run() {

        AlgoritmeTester tester = new AlgoritmeTester("BruteForce Convex Hull");
        for(int i = 20; i < 40; i += 20) {

            /*
            BruteForceConvex bruteForcer = new BruteForceConvex(i);
            bruteForcer.getPrinter().stopPrints();
            tester.test(bruteForcer, 1, i);
            if(i == 100) this.paintComponents.add(bruteForcer.getPaintInfo());
            */

            /*
            AndrewsIncremental andrews = new AndrewsIncremental(i);
            andrews.getPrinter().stopPrints();
            tester.test(andrews, 1, i);
            this.paintComponents.add(andrews.getPaintInformation());
            */

            GrahamScan grahamScan = new GrahamScan(i);
            tester.test(grahamScan, 1, i);
            this.paintComponents.add(grahamScan.getPaintInfo());

        }

        tester.results();


    }

    public void paint(Graphics2D graphics) {
        for(PaintInformation p : paintComponents)
            p.paint(graphics);
    }

}
