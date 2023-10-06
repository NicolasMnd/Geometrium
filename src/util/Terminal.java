package util;

import intersection.*;
import sorting.DoorlooplijnQuicksort;
import util.blueprints.AlgoritmeTester;
import util.blueprints.PaintInformation;
import util.math.Segment;

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

        /*
        BruteForceSegment bfs = new BruteForceSegment(10);
        bfs.run();
        this.paintComponents.add(bfs.getPaintInformation());
         */

        /*
        IntersectionGenerator gen = new IntersectionGenerator(10);
        gen.compose();
        paintComponents.add(new PaintInformationLine(new Segment[]{}, gen.getList()));
                 */

        Doorlooplijn d = new Doorlooplijn(10);
        d.run();
        paintComponents.add(d.getPaintInformation());

    }

    private void testConvex() {
        AlgoritmeTester tester = new AlgoritmeTester("BruteForce Convex Hull");
        for(int i = 40; i < 41; i += 20) {

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
            if(i == 100) this.paintComponents.add(andrews.getPaintInfo());
            */

            /*
            GrahamScan grahamScan = new GrahamScan(i);
            grahamScan.getPrinter().stopPrints();
            tester.test(grahamScan, 1, i);
            this.paintComponents.add(grahamScan.getPaintInfo());

            /**
            JarvisMarch jarvisMarch = new JarvisMarch(i);
            tester.test(jarvisMarch, 1, i);
            this.paintComponents.add(jarvisMarch.getPaintInfo());
             */

        }

        tester.results();
    }

    public void paint(Graphics2D graphics) {
        for(PaintInformation p : paintComponents)
            p.paint(graphics);
    }

}
