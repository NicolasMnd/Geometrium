package convexhull;

import sorting.QuickSort;
import util.math.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JarvisMarch extends ConvexHullAlgoritme {

    private float[] sortedAngles;

    public JarvisMarch(Pos[] positions) {
        super("Jarvis March", positions);
    }

    public JarvisMarch(int size) {
        super("Jarvis March", size);
    }

    public Pos getLowestY(Pos[] positions) {

        Pos lowestY = positions[0];

        for(Pos testPos : positions)
            if(testPos.y() < lowestY.y()) lowestY = testPos;

        return lowestY;

    }

    public Pos getLowestAngle(List<Pos> positions, Pos lowestY, float minimalAngle) {

        Pos shiftLowest = lowestY.shiftX(1);
        // end - start;
        Pos referencer = shiftLowest.minus(lowestY);
        Map<Pos, Float> posFloatMap = new HashMap<>();
        float[] floatMap = new float[positions.size()];
        int floatMapIndex = 0;

        printer.print("lowestY : ", lowestY.getPrint());

        // Calculating the angles
        for(Pos testPos : positions) {

            if(!testPos.equals(lowestY)) {

                testPos = testPos.shiftXY(-lowestY.x(), -lowestY.y());
                float ang = (float) Math.atan2(referencer.x()*testPos.y() - (testPos.x()*referencer.y()), referencer.x()*testPos.x() + (referencer.y()*testPos.y()));
                float angle = (float) ((180/Math.PI) * ang) % (float) (Math.PI * 2);
                testPos = testPos.shiftXY(lowestY.x(), lowestY.y());

                printer.print("Calculating angle of ", testPos.getPrint() + " = " + angle);

                posFloatMap.put(testPos, angle);
                floatMap[floatMapIndex++] = angle;

            }

        }

        // Sorting the angles
        QuickSort quickSort = new QuickSort();
        float[] sortedAngles = quickSort.sort(floatMap);

        // Sorting the positions
        List<Pos> sortedByAngle = new ArrayList<>();
        int helpIdx = 0;
        float[] least = new float[sortedAngles.length];
        for(int i = 0; i < sortedAngles.length; i++) {
            for(Map.Entry<Pos, Float> ent : posFloatMap.entrySet()) {
                if(ent.getValue() == sortedAngles[i] && sortedAngles[i] >= minimalAngle) {
                    sortedByAngle.add(ent.getKey());
                    least[helpIdx++] = sortedAngles[i];
                }
            }
        }

        this.sortedAngles = least.length == 0 ? sortedAngles : least;

        return sortedByAngle.size() == 0 ? null : sortedByAngle.get(0);

    }

    @Override
    public void run() {

        List<Pos> positions = getList(getCoordinates());
        List<Pos> convexes = new ArrayList<>();
        Pos lowest = getLowestY(getCoordinates());

        // We remove the lowest position, because that is our first one
        positions.remove(lowest);
        convexes.add(lowest);

        float minimal_angle = 0;

        while(positions.size() > 0 && lowest != null) {

            Pos smallestAngle = getLowestAngle(positions, lowest, minimal_angle);
            if(smallestAngle == null) break;
            positions.remove(smallestAngle);
            convexes.add(smallestAngle);
            lowest = smallestAngle;
            minimal_angle = sortedAngles[0];

        }

        this.setConvexes(convexes);

    }

    public List<Pos> getList(Pos[] positions) {
        List<Pos> list = new ArrayList<>();
        for(Pos p : positions)
            list.add(p);
        return list;
    }

}
