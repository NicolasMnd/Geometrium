package intersection;

import sorting.DoorlooplijnQuicksort;
import sorting.QuickSort;
import util.math.Pos;
import util.math.Segment;

import java.util.ArrayList;
import java.util.List;

public class Doorlooplijn extends LineIntersectionAlgorithm {

    private float[] sortedY;
    private final boolean sortOnX;

    public Doorlooplijn(int size, boolean sortOnX) {
        super("Doorlooplijn", size);
        this.sortOnX = sortOnX;
    }

    public Segment[] sortOnY() {

        DoorlooplijnQuicksort quickSort = new DoorlooplijnQuicksort();
        return quickSort.sort(getSegments());

    }

    public List<Segment> getSegments(List<Segment> actives, float yLevel) {
        List<Segment> modified = new ArrayList<>();
        for(Segment s : actives) {
            if(s.lowestY() < yLevel) modified.add(s);
        }
        return modified;
    }

    @Override
    public void run() {

        // All segments where the y-level intersects
        List<Segment> active = new ArrayList<>();

        // If we sort on X, we will order the segments on their top Y posses X coordinate
        List<Segment> status = new ArrayList<>();

        // We put all intersections in here.
        List<Segment> intersections = new ArrayList<>();

        // For this algorithm, we will always sort our segments on Y
        Segment[] segments = sortOnY();

        // We will loop over y-coordinates. We skip the highest since on this level only that segment is there.
        int idx = segments.length-1;

        // We loop through all segments starting from the highest position
        while(idx >= 0) {

            // Select Y level and add its corresponding segment
            float yLevel = segments[idx].highestY();
            active.add(segments[idx]);

            // Update actives, the ones that are above the Y level are removed.
            active = getSegments(active, yLevel);

            // Find intersections
            if(sortOnX) {

                // IF we sort on X, we will only check direct neighbours.
                if(status.size() >= 2) {



                }

            } else {

                for (Segment s : active)
                    for (Segment t : active)
                        if (s != t && intersect(s, t)) {
                            intersections.add(s);
                            intersections.add(t);
                        }

            }

            idx--;

        }

        this.setIntersections(intersections);

    }

}
