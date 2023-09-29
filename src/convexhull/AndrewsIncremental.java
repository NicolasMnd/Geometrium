package convexhull;

import sorting.QuickSort;
import util.blueprints.PaintInformation;
import util.math.Pos;
import util.math.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AndrewsIncremental extends ConvexHullAlgoritme {

    private Pos[] sortedPositions;

    public AndrewsIncremental(Pos[] positions) {
       super("Andrews Incremental", positions);
    }

    public AndrewsIncremental(int size) {
        super("Andrews Incremental", size);
    }

    private float[] getXCoordinates(Pos[] coordinates) {

        float[] xCoords = new float[coordinates.length];

        for(int i = 0; i < coordinates.length; i++)
            xCoords[i] = coordinates[i].x();

        return xCoords;

    }

    private Pos[] getSortedPos(float[] xCoords, Pos[] coords) {
        Pos[] sortedArray = new Pos[xCoords.length];

        for(int i = 0; i < xCoords.length; i++) {
            for(int j = 0; j < coords.length; j++)
                if (xCoords[i] == coords[j].x()) sortedArray[i] = coords[j];
        }

        return sortedArray;
    }

    @Override
    public void run() {

        LinkedList<Pos> convexPoints = new LinkedList<>();
        QuickSort quickSorter = new QuickSort();
        float[] sortedX = quickSorter.sort(getXCoordinates(getCoordinates()));
        this.sortedPositions = getSortedPos(sortedX, getCoordinates());
        Pos[] coordinates = this.sortedPositions;

        if(coordinates.length == 0) return;

        int indexCoordinates = 0;

        // We voegen maximaal n coordinaten toe.
        for(int i = 0; i < coordinates.length; i++) {

            while(convexPoints.size() >= 3) {

                printer.print("Size is greater than 3. Checking for errors");

                int size = convexPoints.size();
                Pos last = convexPoints.get(size-3);
                Pos middle = convexPoints.get(size-2);
                Pos first = convexPoints.get(size-1);

                if(cross(middle.minus(last), first.minus(last)) > 0) {

                    convexPoints.remove(size-2);
                    printer.print("Removing point from list so size is now " + convexPoints.size());

                } else break;

            }

            convexPoints.add(coordinates[i]);

            printer.print("i: " + i);

        }

        int upperAmount = convexPoints.size();

        for(int i = coordinates.length-2; i >= 0; i--) {

            while(convexPoints.size() >= upperAmount) {

                printer.print("Size is greater than 3. Checking for errors");

                int size = convexPoints.size();
                Pos last = convexPoints.get(size-3);
                Pos middle = convexPoints.get(size-2);
                Pos first = convexPoints.get(size-1);

                if(cross(middle.minus(last), first.minus(last)) > 0) {

                    convexPoints.remove(size-2);
                    printer.print("Removing point from list so size is now " + convexPoints.size());

                } else break;

            }

            convexPoints.add(coordinates[i]);

            printer.print("i: " + i);

        }

        this.setConvexes(convexPoints);

    }

    public PaintInformation getPaintInformation() {
        return new PaintInformationConvex(getCoordinates(), getConvexes());
    }

    private void printList(LinkedList<Pos> convexPoints) {
        for(Pos p : convexPoints)
            printer.print(p.getPrint(), ", ");
    }

}
