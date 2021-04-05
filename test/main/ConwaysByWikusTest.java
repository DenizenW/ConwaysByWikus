package main;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConwaysByWikusTest {
    private static ConwaysByWikus conways;

    @BeforeAll
    static void initialize() {
        boolean[][] initialState = {{false, true, false, true},      // 0 1 0 1
                                    {true, true, false, false},      // 1 1 0 0
                                    {true, true, false, false},      // 1 1 0 0
                                    {false, false, false, false}};   // 0 0 0 0
        conways = new ConwaysByWikus(initialState);
    }

    @ParameterizedTest
    @MethodSource("sourceLiveCellWithFewerThanTwoLiveNeighboursDies")
    public void testLiveCellWithFewerThanTwoLiveNeighboursDies(Point point, boolean expected) {
        boolean result = conways.liveCellWithFewerThanTwoLiveNeighboursDies(point);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> sourceLiveCellWithFewerThanTwoLiveNeighboursDies() {
        return Stream.of(
                Arguments.of(new Point(0, 0), false), // dead cell
                Arguments.of(new Point(0, 3), true),  // live cell with <2 neighbours
                Arguments.of(new Point(1, 1), false)  // live cell with >=2 neighbours
        );
    }

    @ParameterizedTest
    @MethodSource("sourceLiveCellWithTwoOrThreeLiveNeighboursLives")
    public void testLiveCellWithTwoOrThreeLiveNeighboursLives(Point point, boolean expected) {
        boolean result = conways.liveCellWithTwoOrThreeLiveNeighboursLives(point);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> sourceLiveCellWithTwoOrThreeLiveNeighboursLives() {
        return Stream.of(
                Arguments.of(new Point(3, 3), false), // dead cell
                Arguments.of(new Point(0, 1), true),  // live cell with 2 neighbours
                Arguments.of(new Point(1, 1), false)  // live cell with >3 neighbours
        );
    }

    @ParameterizedTest
    @MethodSource("sourceLiveCellWithMoreThanThreeLiveNeighboursDies")
    public void testLiveCellWithMoreThanThreeLiveNeighboursDies(Point point, boolean expected) {
        boolean result = conways.liveCellWithMoreThanThreeLiveNeighboursDies(point);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> sourceLiveCellWithMoreThanThreeLiveNeighboursDies() {
        return Stream.of(
                Arguments.of(new Point(0, 2), false), // dead cell
                Arguments.of(new Point(2, 1), false), // live cell with <=3 neighbours
                Arguments.of(new Point(1, 0), true)   // live cell with >3 neighbours
        );
    }

    @ParameterizedTest
    @MethodSource("sourceDeadCellWithExactlyThreeLiveNeighboursBecomesAlive")
    public void testDeadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point, boolean expected) {
        boolean result = conways.deadCellWithExactlyThreeLiveNeighboursBecomesAlive(point);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> sourceDeadCellWithExactlyThreeLiveNeighboursBecomesAlive() {
        return Stream.of(
                Arguments.of(new Point(2, 0), false), // live cell
                Arguments.of(new Point(2, 2), false), // dead cell with <3 neighbours
                Arguments.of(new Point(0, 2), true),  // dead cell with 3 neighbours
                Arguments.of(new Point(0, 0), false), // dead cell with >3 neighbours
                Arguments.of(new Point(1, 2), false)  // dead cell with >3 neighbours
        );
    }

    @ParameterizedTest
    @MethodSource("sourceStep")
    public void testStep(ConwaysByWikus initialState, boolean[][] expected) {
        initialState.step();
        boolean[][] newState = initialState.getCurrentGrid();
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], newState[i]);
        }
    }

    private static Stream<Arguments> sourceStep() {
        ConwaysByWikus conways1 = new ConwaysByWikus(2, 3);
        boolean[][] expected1 = {{false, false, false}, {false, false, false}};

        boolean[][] initial2 = {{false, true, false, true},      // 0 1 0 1
                                {true, true, false, false},      // 1 1 0 0
                                {true, true, false, false}};     // 1 1 0 0
        ConwaysByWikus conways2 = new ConwaysByWikus(initial2);
        boolean[][] expected2 = {{false, false, false, true},    // 0 0 0 1
                                 {false, false, false, true},    // 0 0 0 1
                                 {false, false, false, true}};   // 0 0 0 1

        boolean[][] initial3 = {{true, false, false, true},      // 1 0 0 1
                                {false, true, true, false},      // 0 1 1 0
                                {true, false, true, false}};     // 1 0 1 0
        ConwaysByWikus conways3 = new ConwaysByWikus(initial3);
        boolean[][] expected3 = {{true, false, false, false},    // 1 0 0 0
                                 {false, false, true, false},    // 0 0 1 0
                                 {true, false, true, false}};    // 1 0 1 0

        return Stream.of(
                Arguments.of(conways1, expected1),   // empty grid
                Arguments.of(conways2, expected2),
                Arguments.of(conways3, expected3)
        );
    }
}
