package main;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConwaysByWikusTest {

    @Test
    public void testLiveCellWithFewerThanTwoLiveNeighboursDies() {
        boolean[][] initialState = {{false, false, false},    // 0 0 0
                                    {true, true, false},      // 1 1 0
                                    {false, false, false}};   // 0 0 0
        ConwaysByWikus conways = new ConwaysByWikus(initialState);
        boolean result = conways.liveCellWithFewerThanTwoLiveNeighboursDies(new Point(1, 1));
        assertEquals(true, result);
    }

    @Test
    public void testLiveCellWithTwoOrThreeLiveNeighboursLives() {
        boolean[][] initialState = {{false, true, false},     // 0 1 0
                                    {true, true, false},      // 1 1 0
                                    {false, false, false}};   // 0 0 0
        ConwaysByWikus conways = new ConwaysByWikus(initialState);
        boolean result = conways.liveCellWithTwoOrThreeLiveNeighboursLives(new Point(1, 1));
        assertEquals(true, result);
    }

    @Test
    public void testLiveCellWithMoreThanThreeLiveNeighboursDies() {
        boolean[][] initialState = {{false, true, false},     // 0 1 0
                                    {true, true, false},      // 1 1 0
                                    {true, true, false}};     // 1 1 0
        ConwaysByWikus conways = new ConwaysByWikus(initialState);
        boolean result = conways.liveCellWithMoreThanThreeLiveNeighboursDies(new Point(1, 1));
        assertEquals(true, result);
    }
}
