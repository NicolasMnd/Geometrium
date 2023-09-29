package convexhull;

import util.math.Pos;
import util.helpers.Printer;

import java.util.ArrayList;
import java.util.List;

public class BruteForceConvex extends ConvexHullAlgoritme {

    public BruteForceConvex(Pos[] positions) {
        super("brute force convex", positions);
    }
    public BruteForceConvex(int size) {
        super("brute force convex", size);
    }

    @Override
    public void run() {

        List<Pos> convexPoints = new ArrayList<>();
        Pos[] coordinates = getCoordinates();
        int firstCoordinateIndex = 0;
        int secondCoordinateIndex = 0;

        // We go over each coordinate
        while (firstCoordinateIndex < coordinates.length) {

            // We select a second coordinate
            while (secondCoordinateIndex < coordinates.length) {

                if(firstCoordinateIndex != secondCoordinateIndex)
                    printer.print(Printer.BLUE_BOLD_BRIGHT, "Testing ", Printer.WHITE, coordinates[firstCoordinateIndex].getPrint(), " - ", coordinates[secondCoordinateIndex].getPrint());

                // We check each coordinate
                if (firstCoordinateIndex != secondCoordinateIndex && this.allDataRight(coordinates, coordinates[firstCoordinateIndex], coordinates[secondCoordinateIndex])) {
                    convexPoints.add(coordinates[firstCoordinateIndex]);
                    convexPoints.add(coordinates[secondCoordinateIndex]);
                }

                secondCoordinateIndex++;

            }

            secondCoordinateIndex = 0;
            firstCoordinateIndex++;

        }

        this.setConvexes(convexPoints);

    }

    public PaintInformationConvex getPaintInfo() {
        return new PaintInformationConvex(getCoordinates(), getConvexes());
    }

}
