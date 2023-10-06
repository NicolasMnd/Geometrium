package util.math;

import java.awt.*;

public class Segment {

    private Pos start, end;
    private Color color;

    public Segment(Pos a, Pos b) {
        this.start = a;
        this.end = b;
    }

    public float highestY() {
        return start.y() > end.y() ? start.y() : end.y();
    }

    public float lowestY() {
        return start.y() > end.y() ? end.y() : start.y();
    }

    public Segment(Pos a, Pos b, int idx) {
        this.start = a;
        this.end = b;
        this.color = getColor(idx);
    }

    public String getPrint() {
        return "[" + start.x() + ", " + start.y() + "] -> [" + end.x() + ", " + end.y() + "] (" + getColor() + ")";
    }

    private Color getColor(int idx) {
        switch(idx) {
            case 0:
                return Color.RED;
            case 1:
                return Color.ORANGE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.CYAN;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.PINK;
            case 8:
                return Color.GRAY;
            case 9:
                return Color.DARK_GRAY;
        }
        return Color.WHITE;
    }

    public float cross(Pos test) {
        test = test.minus(start);
        Pos end = this.end.minus(start);
        return (end.x()*test.y()) - (end.y()*test.x());
    }

    private float cross(Pos a, Pos b, Pos c) {
        b = b.minus(a);
        c = c.minus(a);
        return (b.x()*c.y()) - (b.y()*c.x());
    }

    public boolean isColinear(Pos a) {
        return cross(start, end, a) == 0;
    }

    public boolean isLeft(Pos a) {
        return cross(start, end, a) > 0;
    }

    public Pos getStart() {
        return this.start;
    }

    public String getColor() {
        if(color == Color.RED) return "Red";
        if(color == Color.ORANGE) return "Orange";
        if(color == Color.YELLOW) return "Yellow";
        if(color == Color.BLUE) return "Blue";
        if(color == Color.CYAN) return "Cyan";
        if(color == Color.MAGENTA) return "Magenta";
        if(color == Color.GREEN) return "Green";
        if(color == Color.PINK) return "Pink";
        if(color == Color.DARK_GRAY) return "Dark gray";
        else return "Gray";
    }
    public Pos getEnd() {
        return this.end;
    }

    public void drawSegment(Graphics2D graphics) {


        /*
        Stroke stroke1 = new BasicStroke(1f);
        graphics.setColor(color);
        graphics.setStroke(stroke1);

         */

        graphics.drawLine((int) start.x()*10, (int) start.y()*10, (int) end.x()*10, (int) end.y()*10);

    }

}
