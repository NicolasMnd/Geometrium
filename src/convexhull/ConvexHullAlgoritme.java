package convexhull;

import sorting.QuickSort;
import util.blueprints.Algoritme;
import util.helpers.Printer;
import util.helpers.PuntenGenerator;
import util.math.Pos;
import util.math.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ConvexHullAlgoritme extends Algoritme {

    private PuntenGenerator generator;
    private final Pos[] positions;
    private List<Pos> convexes;

    public ConvexHullAlgoritme(String name, Pos[] positions) {
        super(name);
        this.generator = new PuntenGenerator();
        this.positions = positions;
    }

    public ConvexHullAlgoritme(String name, int size) {
        super(name);
        this.generator = new PuntenGenerator();
        this.positions = generator.get2DCoordinateArray(size, 30, 30);
    }

    public Pos[] getCoordinates() {
        return this.positions;
    }

    /**
     * Only call after running!
     */
    public List<Pos> getConvexes() {
        return this.convexes;
    }

    public void setConvexes(List<Pos> convexes) {
        this.convexes = convexes;
    }

    public boolean areEqual(Pos coordinate1, Pos coordinate2) {
        return coordinate1.equals(coordinate2);
    }

    public float cross(Pos pos1, Pos pos2) {

        //printer.print("Cross product :" + Float.toString((pos1.x() * pos2.y()) - (pos1.y() * pos2.x())));
        return (pos1.x() * pos2.y()) - (pos1.y() * pos2.x());

    }

    public boolean allDataRight(Pos[] coordinates, Pos startPoint, Pos endPoint) {

        Pos vector = endPoint.minus(startPoint);

        for(Pos coordinate : coordinates) {

            // We only look at other points than startPoint and endPoint
            if(!areEqual(coordinate, startPoint) && !areEqual(coordinate, endPoint)) {

                Pos vector2 = coordinate.minus(startPoint);

                if(cross(vector, vector2) > 0) {
                    printer.print(Printer.WHITE_BOLD_BRIGHT, "  Comparing with ", coordinate.getPrint(), Printer.RED_BOLD_BRIGHT, "  OK");
                    return false;
                }

                printer.print(Printer.WHITE_BOLD_BRIGHT, "  Comparing with ", coordinate.getPrint(), Printer.GREEN_BOLD_BRIGHT, "  OK");

            }



        }

        return true;

    }

    public PaintInformationConvex getPaintInfo() {
        System.out.println("Convexes length: " + getConvexes().size());
        return new PaintInformationConvex(getCoordinates(), getConvexes());
    }


}
