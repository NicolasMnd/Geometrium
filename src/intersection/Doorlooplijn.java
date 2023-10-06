package intersection;

import com.sun.jdi.Field;
import sorting.DoorlooplijnQuicksort;
import sorting.QuickSort;
import util.cs.OperationTime;
import util.math.Pos;
import util.math.Segment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Doorlooplijn extends LineIntersectionAlgorithm {

    private float[] sortedY;
    private List<Integer> amountPer = new ArrayList<>();

    public Doorlooplijn(int size) {
        super("Doorlooplijn", size);
    }

    private Segment[] getCopy(Segment[] arr) {
        Segment[] segments = new Segment[arr.length];
        for(int i = 0; i < arr.length; i++) segments[i] = arr[i];
        return segments;
    }

    public Segment[] sortOnLowestY() {

        DoorlooplijnQuicksort quickSort = new DoorlooplijnQuicksort();
        quickSort.setFieldSelector(Segment::lowestY);
        Segment[] copy = getCopy(getSegments());
        Segment[] sortedBot = quickSort.sort(copy);

        return sortedBot;

    }

    public Segment[] sortOnHighestY() {

        DoorlooplijnQuicksort quickSort = new DoorlooplijnQuicksort();
        quickSort.setFieldSelector(Segment::highestY);
        Segment[] copy = getCopy(getSegments());
        Segment[] sortedTop = quickSort.sort(copy);

        return sortedTop;

    }

    public Set<Segment> getSegments(Set<Segment> actives, float yLevel) {
        Set<Segment> modified = new HashSet<>();
        for(Segment s : actives) {
            if(s.lowestY() < yLevel) {
                modified.add(s);
            } //else printer.print("Ignoring segment colored " + s.getColor());
        }
        return modified;
    }

    @Override
    public void run() {

        // All segments where the y-level intersects
        Set<Segment> active = new HashSet<Segment>();

        // We put all intersections in here.
        Set<Segment> intersections = new HashSet<Segment>();

        // For this algorithm, we will always sort our segments on Y
        Segment[] segmentsTop = sortOnHighestY();
        Segment[] segmentsBot = sortOnLowestY();

        // We will loop over y-coordinates. We skip the highest since on this level only that segment is there.
        int higher = segmentsTop.length-1;
        int lower = segmentsBot.length-1;

        // We loop through all segments starting from the highest position
        while(higher >=  0 && lower >= 0) {

            float yLevel = -1;
            Segment newSegment = null;

            // Select Y level and add its corresponding segment
            if(segmentsTop[higher].highestY() > segmentsBot[lower].lowestY()) {
                yLevel = segmentsTop[higher].highestY();
                active.add(segmentsTop[higher]);
                newSegment = segmentsTop[higher];
                higher--;
            } else {
                yLevel = segmentsBot[lower].lowestY();
                lower--;
            }

            int lenBefore = active.size();
            active = getSegments(active, yLevel);

            if(lenBefore <= active.size() && newSegment != null)
                for (Segment s : active)
                    if (s != newSegment && intersect(s, newSegment)) {
                        intersections.add(s);
                        intersections.add(newSegment);
                    }

        }



        this.setIntersections(intersections);
        System.out.println(getIntersectionCalculations());


    }

}
