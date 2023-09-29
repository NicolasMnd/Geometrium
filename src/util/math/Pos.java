package util.math;

public class Pos {

    private final float x, y;

    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Pos minus(Pos pos) {
        return new Pos(x - pos.x, y - pos.y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pos) {
            Pos posObj = (Pos) obj;
            return posObj.x == x && posObj.y == y;
        } else
            return super.equals(obj);
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public Pos shiftX(float x) {
        return new Pos(this.x + x, this.y);
    }

    public Pos shiftY(float y) {
        return new Pos(this.x, this.y + y);
    }

    public Pos shiftXY(float x, float y) {
        return new Pos(this.x + x, this.y + y);
    }

    public String getPrint() {
        return "[" + x + ", " + y + "]";
    }

}