package intersection;

import util.helpers.PuntenGenerator;
import util.math.Pos;
import util.math.Segment;

import java.util.ArrayList;
import java.util.List;

public class IntersectionGenerator {

    private List<Segment> segmentList;
    private int size;

    public IntersectionGenerator(int size) {
        this.size = size;
    }

    public List<Segment> getList() {
        return segmentList;
    }

    public void compose() {

        List<Segment> correctSegments = new ArrayList<>();
        PuntenGenerator generator = new PuntenGenerator();
        int aantal = 0;

        while(aantal < size) {

            // We creëren nieuwe segment posities
            Pos[] inspect = generator.get2DCoordinateArray(2, 30, 30);
            Segment newSegment = new Segment(inspect[0], inspect[1]);

            // We maken een nieuwe lijst
            List<Segment> checkNew = new ArrayList<>();

            // We steken onze nieuwe en oudere erbij
            checkNew.add(newSegment);
            checkNew.addAll(correctSegments);

            BruteForceSegment segmentChecker = new BruteForceSegment(toArray(checkNew));
            segmentChecker.run();

            if(!segmentChecker.hasIntersections()) {
                correctSegments = checkNew;
                aantal++;
            }

        }

        this.segmentList = correctSegments;


    }

    public Segment[] toArray(List<Segment> segmentList) {

        Segment[] segArr = new Segment[segmentList.size()];

        for(int i = 0; i < segmentList.size(); i++)
            segArr[i] = segmentList.get(i);

        return segArr;

    }

}
