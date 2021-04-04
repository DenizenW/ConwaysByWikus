package main;

public class Main {

    public static void main(String[] args) {
        boolean[][] initialState = {{false, false, false, false, false},  // 0 0 0 0 0
                                    {false, false, true, false, false},   // 0 0 1 0 0
                                    {false, false, true, false, false},   // 0 0 1 0 0
                                    {false, false, true, false, false},   // 0 0 1 0 0
                                    {false, false, false, false, false}}; // 0 0 0 0 0
        ConwaysByWikus conways = new ConwaysByWikus(initialState);
        ConwayGui conwayGui = new ConwayGui(conways);
    }
}
