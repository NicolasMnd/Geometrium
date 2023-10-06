package intersection;

import util.blueprints.PaintInformation;
import util.math.Segment;

import java.awt.*;
import java.util.List;
import java.util.Set;

public class PaintInformationLine extends PaintInformation {

    private final Segment[] segments;
    private final Set<Segment> segmentList;

    public PaintInformationLine(Segment[] segments, Set<Segment> intersections) {
        this.segments = segments;
        this.segmentList = intersections;
    }

    @Override
    public void paint(Graphics2D graphics) {

        Stroke stroke1 = new BasicStroke(1f);
        graphics.setColor(Color.WHITE);
        graphics.setStroke(stroke1);

        for(int i = 0; i < segments.length; i++) {

            segments[i].drawSegment(graphics);

        }

        graphics.setColor(Color.RED);


        for(Segment s : segmentList)
            s.drawSegment(graphics);

    }

}
