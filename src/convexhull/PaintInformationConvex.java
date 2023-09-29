package convexhull;

import util.blueprints.PaintInformation;
import util.math.Pos;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PaintInformationConvex extends PaintInformation {

    private final Pos[] positions;
    private final List<Pos> convexes;
    private final List<Vector> lines;

    public PaintInformationConvex(Pos[] positions, List<Pos> convexes) {
        this.positions = positions;
        this.convexes = convexes;
        this.lines = determineLines();
    }

    private List<Vector> determineLines() {
        List<Vector> lines = new ArrayList<>();



        return lines;
    }

    @Override
    public void paint(Graphics2D graphics) {

        Stroke stroke1 = new BasicStroke(2f);
        graphics.setColor(Color.WHITE);
        graphics.setStroke(stroke1);
        for(Pos p : positions)
            graphics.drawOval((int) p.x()*10, (int) p.y()*10, 4, 4);

        graphics.setColor(Color.RED);
        for(Pos p : convexes)
            graphics.drawOval((int) p.x()*10, (int) p.y()*10, 4, 4);


    }

}
