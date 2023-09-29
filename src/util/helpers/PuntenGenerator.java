package util.helpers;

import util.math.Pos;

import java.util.Random;

public class PuntenGenerator {

    private final Random rand = new Random();
    private final Printer printer = new Printer("PuntenGenerator");

    public PuntenGenerator() {
    }

    private float getRandom(int max, int min) {
        return rand.nextFloat() * (max - min) + min;
    }

    private final boolean exists(Pos[] coordinates, float genX, float genY) {

        if(coordinates.length == 0) return false;

        for(Pos coordinate : coordinates) {
            if (coordinate != null && coordinate.x() == genX && coordinate.y() == genY)
                return true;

        }

        return false;
    }

    public Pos[] get2DCoordinateArray(int size, int maxX, int maxY) {

        Pos[] coordinates = new Pos[size];
        int count = 0;

        while(count < size) {

            float genX = getRandom(0, maxX);
            float genY = getRandom(0, maxY);

            if(!exists(coordinates, genX, genY)) {

                coordinates[count] = new Pos(genX, genY);

            }

            count++;

        }


        return coordinates;

    }

}
