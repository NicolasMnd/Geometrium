package beziercurves;

import util.blueprints.PaintInformation;
import util.math.Pos;

import java.awt.*;

public class BezierPaintInformation extends PaintInformation {

    private final Pos[] curve, control;

    public BezierPaintInformation(Pos[] curve, Pos[] control) {
        this.curve = curve;
        this.control = control;
    }


    @Override
    public void paint(Graphics2D graphics) {
        Stroke stroke1 = new BasicStroke(2f);
        graphics.setColor(Color.WHITE);
        graphics.setStroke(stroke1);
        for(Pos p : curve)
            graphics.drawOval((int) p.x()*100, (int) p.y()*100, 4, 4);

        graphics.setColor(Color.RED);
        for(Pos p : control)
            if(p != null)
                graphics.drawOval((int) p.x()*100, (int) p.y()*100, 4, 4);

        System.out.println(control[control.length-1].x());
    }

}
