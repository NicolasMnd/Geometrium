package intersection;

import util.math.Segment;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSegment extends LineIntersectionAlgorithm {

    public BruteForceSegment(int size) {
        super("Brute Force Line Segment", size);
    }

    public BruteForceSegment(Segment[] segments) {
        super("Brute Force Line Segment", segments);
    }

    @Override
    public void run() {

        Segment[] segments = getSegments();
        List<Segment> intersections = new ArrayList<>();

        for(int i = 0; i < segments.length; i++) {

            for(int j = 0; j < segments.length; j++) {

                Segment segA = segments[i];
                Segment segB = segments[j];

                // We won't look at the same segments
                if(j != i && !this.areColinear(segA, segB)) {

                    if(intersect(segA, segB)) {
                        printer.print("Segment " + segA.getColor(), " intersects " + segB.getColor());
                        intersections.add(segA);
                        intersections.add(segB);
                    }

                }

            }

        }

        setIntersections(intersections);

    }

}
