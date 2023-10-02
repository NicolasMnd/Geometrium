package convexhull;

import sorting.QuickSort;
import util.math.Pos;

import java.util.*;

public class GrahamScan extends ConvexHullAlgoritme {

    private float[] sortedAngles;

    public GrahamScan(Pos[] positions) {
        super("Graham Scan", positions);
    }

    public GrahamScan(int size) {
        super("Graham Scan", size);
    }

    private Pos getLowestY(Pos[] positions) {

        Pos lowestY = positions[0];

        for(Pos testPos : positions)
            if(testPos.y() < lowestY.y()) lowestY = testPos;

        return lowestY;

    }

    private Pos[] sortByAngle(Pos[] positions) {

        Pos lowestY = getLowestY(positions);
        Pos shiftLowest = lowestY.shiftX(1);
        // end - start;
        Pos referencer = shiftLowest.minus(lowestY);
        Map<Pos, Float> posFloatMap = new HashMap<>();
        float[] floatMap = new float[positions.length-1];
        int floatMapIndex = 0;

        printer.print("lowestY : ", lowestY.getPrint());

        // Calculating the angles
        for(Pos testPos : positions) {

            if(!testPos.equals(lowestY)) {

                testPos = testPos.shiftX(-lowestY.x());
                float inproduct = (referencer.x() * testPos.x()) + (referencer.y() * testPos.y());
                float normTest = (float) Math.sqrt((testPos.x() * testPos.x()) + (testPos.y() * testPos.y()));
                float normReference = (float) Math.sqrt((referencer.x() * referencer.x()) + (referencer.y() * referencer.y()));
                float angle = (float) Math.acos(inproduct / (normTest * normReference));
                testPos = testPos.shiftX(lowestY.x());

                printer.print("Calculating angle of ", testPos.getPrint() + " = " + angle);

                posFloatMap.put(testPos, angle);
                floatMap[floatMapIndex++] = angle;

            }

        }

        // Sorting the angles
        QuickSort quickSort = new QuickSort();
        float[] sortedAngles = quickSort.sort(floatMap);

        // Sorting the positions
        Pos[] sortedByAngle = new Pos[positions.length-1];
        for(int i = 0; i < sortedAngles.length; i++) {
            for(Map.Entry<Pos, Float> ent : posFloatMap.entrySet()) {
                if(ent.getValue() == sortedAngles[i])
                    sortedByAngle[i] = ent.getKey();
            }
        }

        this.sortedAngles = sortedAngles;

        return sortedByAngle;

    }

    @Override
    public void run() {

        LinkedList<Pos> convexPoints = new LinkedList<>();
        Pos[] coordinates = sortByAngle(getCoordinates());
        convexPoints.add(getLowestY(getCoordinates()));

        for(int i = 0; i < coordinates.length; i++) {

            while(convexPoints.size() >= 2) {

                printer.print("Checking for errors with next: ", coordinates[i].getPrint());

                int size = convexPoints.size();
                Pos last = convexPoints.get(size-2);
                Pos middle = convexPoints.get(size-1);
                Pos first = coordinates[i];

                if(cross(middle.minus(last), first.minus(last)) <= 0) {

                    printer.print("Removing", convexPoints.get(size-1).getPrint() ," from list so size is now " + convexPoints.size());
                    convexPoints.remove(size-1);

                } else break;

            }

            printer.print("- Adding coordinate ", coordinates[i].getPrint());
            convexPoints.add(coordinates[i]);

            printer.print("i: " + i + ", Size = " + convexPoints.size());

        }

        this.setConvexes(convexPoints);

    }

}
