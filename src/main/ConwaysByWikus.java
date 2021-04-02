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
        if (!isAlive(point)) return false;
        int[] neighbourhood = getNeighbourhood(point);
        int liveNeighbours = 0;
        for (int row = neighbourhood[0]; row <= neighbourhood[1]; row++) {
            for (int col = neighbourhood[2]; col <= neighbourhood[3]; col++) {
                Point neighbour = new Point(row, col);
                if (!neighbour.equals(point) && isAlive(neighbour)) {
                    liveNeighbours++;
                    if (liveNeighbours >= 2) return false;
                }
            }
        }
        return true;
    }

    // Returns true if the given cell is alive and has two or three live neighbours
    public boolean liveCellWithTwoOrThreeLiveNeighboursLives(Point point) {
        if (!isAlive(point)) return false;
        int[] neighbourhood = getNeighbourhood(point);
        int liveNeighbours = 0;
        for (int row = neighbourhood[0]; row <= neighbourhood[1]; row++) {
            for (int col = neighbourhood[2]; col <= neighbourhood[3]; col++) {
                Point neighbour = new Point(row, col);
                if (!neighbour.equals(point) && isAlive(neighbour)) {
                    liveNeighbours++;
                    if (liveNeighbours > 3) return false;
                }
            }
        }
        return liveNeighbours >= 2;
    }

    // Returns true if the given cell is alive and has more than three live neighbours
    public boolean liveCellWithMoreThanThreeLiveNeighboursDies(Point point) {
        if (!isAlive(point)) return false;
        int[] neighbourhood = getNeighbourhood(point);
        int liveNeighbours = 0;
        for (int row = neighbourhood[0]; row <= neighbourhood[1]; row++) {
            for (int col = neighbourhood[2]; col <= neighbourhood[3]; col++) {
                Point neighbour = new Point(row, col);
                if (!neighbour.equals(point) && isAlive(neighbour)) {
                    liveNeighbours++;
                    if (liveNeighbours > 3) return true;
                }
            }
        }
        return false;
    }

    // Returns true if the given cell is dead and has exactly three live neighbours
    public boolean deadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point) {
        if (isAlive(point)) return false;
        int[] neighbourhood = getNeighbourhood(point);
        int liveNeighbours = 0;
        for (int row = neighbourhood[0]; row <= neighbourhood[1]; row++) {
            for (int col = neighbourhood[2]; col <= neighbourhood[3]; col++) {
                Point neighbour = new Point(row, col);
                if (!neighbour.equals(point) && isAlive(neighbour)) {
                    liveNeighbours++;
                    if (liveNeighbours > 3) return false;
                }
            }
        }
        return liveNeighbours == 3;
    }

    private boolean isAlive(Point point) {
        return currentGrid[point.x][point.y];
    }

    // Returns an array specifying the boundaries of the given point's neighbourhood
    private int[] getNeighbourhood(Point point) {
        int xMin = Math.max(0, point.x - 1);
        int yMin = Math.max(0, point.y - 1);
        int xMax = Math.min(NUM_ROWS - 1, point.x + 1);
        int yMax = Math.min(NUM_COLUMNS - 1, point.y + 1);
        return new int[] {xMin, xMax, yMin, yMax};
    }
}