package main;

import java.awt.*;
import java.util.Arrays;

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

    public ConwaysByWikus(boolean[][] initialState) {
        this.NUM_ROWS = initialState.length;
        this.NUM_COLUMNS = initialState[0].length;
        this.currentGrid = new boolean[NUM_ROWS][];
        this.nextGrid = new boolean[NUM_ROWS][NUM_COLUMNS];
        // Copy initial grid state
        for (int row = 0; row < NUM_ROWS; row++) {
            currentGrid[row] = Arrays.copyOf(initialState[row], NUM_COLUMNS);
        }
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