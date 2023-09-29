package util.math;

public class Vector {

    private final Pos start, end;

    public Vector(Pos start, Pos end) {
        this.start = start;
        this.end = end;
    }

    public Pos getStart() {
        return this.start;
    }

    public Pos getEnd() {
        return this.end;
    }

    public Vector minus(Vector minus) {
        return new Vector(start.minus(minus.getStart()), end.minus(minus.getEnd()));
    }

}
