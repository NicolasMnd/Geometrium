package beziercurves;

import util.math.Pos;

public class BezierCurve {

    private final Pos[] controlPoints;
    private final int min, max;
    private final int POINTS_PER = 10;

    public BezierCurve(Pos[] controlPoints, int min, int max) {
        this.controlPoints = controlPoints;
        this.min = min;
        this.max = max;
    }

    public Pos[] getCurve() {

        final int AMOUNT = (max - min)*POINTS_PER;

        // We create our polynomals
        BernsteinPolynomal[] polynomals = new BernsteinPolynomal[controlPoints.length];
        Pos[] curve = new Pos[AMOUNT];

        // We create a polynomal for each controlpoint
        for(int i = 0; i < controlPoints.length; i++) {
            polynomals[i] = new BernsteinPolynomal(i, controlPoints.length);
        }

        // We calculate the positions of the curve
        for(int points = 0; points < AMOUNT; points++) {

            float valueX = 0f;
            float valueY = 0f;

            // For each point in Pos, we need to calculate the function of x & y
            for(int controlIndex = 0; controlIndex < controlPoints.length; controlIndex++) {

                valueX += polynomals[controlIndex].f(controlPoints[controlIndex].x(), (float) points/(AMOUNT));
                valueY += polynomals[controlIndex].f(controlPoints[controlIndex].y(), (float) points/(AMOUNT));

            }

            curve[points] = new Pos(valueX, valueY);

        }

        return curve;

    }

}
