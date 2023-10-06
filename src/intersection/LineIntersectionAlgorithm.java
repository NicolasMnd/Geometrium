package intersection;

import sorting.DoorlooplijnQuicksort;
import util.blueprints.Algoritme;
import util.blueprints.PaintInformation;
import util.helpers.PuntenGenerator;
import util.math.Pos;
import util.math.Segment;

import javax.sound.sampled.Line;
import java.util.List;

public abstract class LineIntersectionAlgorithm extends Algoritme {

    private Segment[] segments;
    private List<Segment> intersections;
    private int size;
    private int intersectionCalculations = 0;

    public LineIntersectionAlgorithm(String name, int size) {
        super(name);
        this.size = size;
        this.segments = generateSegments();
    }

    public LineIntersectionAlgorithm(String name, Segment[] segments) {
        super(name);
        this.segments = segments;
    }

    public int getIntersectionCalculations() {
        return this.intersectionCalculations;
    }

    public boolean areColinear(Segment segmentA, Segment segmentB) {

        return segmentA.isColinear(segmentB.getStart()) || segmentA.isColinear(segmentB.getEnd()) || segmentB.isColinear(segmentA.getStart()) || segmentB.isColinear(segmentA.getEnd());

    }

    public boolean intersect(Segment segA, Segment segB) {

        intersectionCalculations++;
        // One of the cross product must be positive the other negative, such that one is right and other is left
        return segA.cross(segB.getStart()) * segA.cross(segB.getEnd()) < 0

                && segB.cross(segA.getStart()) * segB.cross(segA.getEnd()) < 0;

    }

    private Segment[] generateSegments() {

        Segment[] segments = new Segment[size];
        PuntenGenerator gen = new PuntenGenerator();
        Pos[] punten = gen.get2DCoordinateArray(size*2, 30, 30);
        int counter = 0;

        for(int i = 0; i < punten.length; i += 2) {

            segments[counter++] = new Segment(punten[i], punten[i+1]);

        }

        return segments;

    }

    public Segment[] getSegments() {
        return this.segments;
    }

    public boolean hasIntersections() {
        return this.intersections.size() > 0;
    }

    public void setIntersections(List<Segment> ints) {
        this.intersections = ints;
    }

    public PaintInformation getPaintInformation() {
        return new PaintInformationLine(segments, intersections);
    }

    public void printSegments(Segment[] segments) {
        for(int i = 0; i < segments.length; i++) {

            printer.print(segments[i].getPrint());

        }
    }

}
