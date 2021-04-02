package main;

import java.awt.*;

public class ConwaysByWikus implements ConwaysGameOfLife {

    final int NUM_ROWS;
    final int NUM_COLUMNS;

    boolean[][] currentGrid;
    boolean[][] nextGrid;

    public ConwaysByWikus(int rows, int columns) {
        this.NUM_ROWS = rows;
        this.NUM_COLUMNS = columns;
        this.currentGrid = new boolean[rows][columns];
        this.nextGrid = new boolean[rows][columns];
    }

    // Returns true if the given cell is alive and has fewer than two live neighbours
    public boolean liveCellWithFewerThanTwoLiveNeighboursDies(Point point) {
        return true;
    }

    // Returns true if the given cell is alive and has two or three live neighbours
    public boolean liveCellWithTwoOrThreeLiveNeighboursLives(Point point) {
        return true;
    }

    // Returns true if the given cell is alive and has more than three live neighbours
    public boolean liveCellWithMoreThanThreeLiveNeighboursDies(Point point) {
        return true;
    }

    // Returns true if the given cell is dead and has exactly three live neighbours
    public boolean deadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point) {
        return true;
    }
}