package main;

import java.awt.*;

public class ConwaysByWikus implements ConwaysGameOfLife {

    public ConwaysByWikus() {
    }

    public boolean liveCellWithFewerThanTwoLiveNeighboursDies(Point point) {
        return true;
    }

    public boolean liveCellWithTwoOrThreeLiveNeighboursLives(Point point) {
        return true;
    }

    public boolean liveCellWithMoreThanThreeLiveNeighboursDies(Point point) {
        return true;
    }

    public boolean deadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point) {
        return true;
    }
}