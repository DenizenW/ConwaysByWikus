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
        this.currentGrid = copyGrid(initialState);
        this.nextGrid = new boolean[NUM_ROWS][NUM_COLUMNS];
    }

    // Returns true if the given cell is alive and has fewer than two live neighbours
    public boolean liveCellWithFewerThanTwoLiveNeighboursDies(Point point) {
        if (!isAlive(point)) return false;
        int liveNeighbours = countLiveNeighbours(point, 2);
        return liveNeighbours < 2;
    }

    // Returns true if the given cell is alive and has two or three live neighbours
    public boolean liveCellWithTwoOrThreeLiveNeighboursLives(Point point) {
        if (!isAlive(point)) return false;
        int liveNeighbours = countLiveNeighbours(point, 4);
        return liveNeighbours == 2 || liveNeighbours == 3;
    }

    // Returns true if the given cell is alive and has more than three live neighbours
    public boolean liveCellWithMoreThanThreeLiveNeighboursDies(Point point) {
        if (!isAlive(point)) return false;
        int liveNeighbours = countLiveNeighbours(point, 4);
        return liveNeighbours > 3;
    }

    // Returns true if the given cell is dead and has exactly three live neighbours
    public boolean deadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point) {
        if (isAlive(point)) return false;
        int liveNeighbours = countLiveNeighbours(point, 4);
        return liveNeighbours == 3;
    }

    // Uses the update rules to compute the successor state of the grid
    public void step() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                Point currentPoint = new Point(row, col);
                if (!currentGrid[row][col]) {
                    // Check if dead cell becomes alive
                    nextGrid[row][col] = deadCellWithExactlyThreeLiveNeighboursBecomesAlive(currentPoint);
                } else {
                    // Check if live cell survives
                    nextGrid[row][col] = liveCellWithTwoOrThreeLiveNeighboursLives(currentPoint);
                }
            }
        }
        boolean[][] temp = currentGrid;
        currentGrid = nextGrid;
        nextGrid = temp;
    }

    // Returns the number of live neighbours for the given point, or cutoff if cutoff is reached
    private int countLiveNeighbours(Point point, int cutoff) {
        int liveNeighbours = 0;
        int[] neighbourhood = getNeighbourhood(point);
        for (int row = neighbourhood[0]; row <= neighbourhood[1]; row++) {
            for (int col = neighbourhood[2]; col <= neighbourhood[3]; col++) {
                Point neighbour = new Point(row, col);
                if (!neighbour.equals(point) && isAlive(neighbour)) {
                    liveNeighbours++;
                    if (liveNeighbours >= cutoff) return cutoff;
                }
            }
        }
        return liveNeighbours;
    }

    public boolean isAlive(Point point) {
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

    public boolean[][] getCurrentGrid() {
        return copyGrid(currentGrid);
    }

    private boolean[][] copyGrid(boolean[][] grid) {
        boolean[][] gridCopy = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            gridCopy[row] = Arrays.copyOf(grid[row], grid[row].length);
        }
        return gridCopy;
    }
}