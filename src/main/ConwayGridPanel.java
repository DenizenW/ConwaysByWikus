package main;

import javax.swing.*;
import java.awt.*;

public class ConwayGridPanel extends JPanel {

    static final Color LIVE_CELL_BACKGROUND = Color.BLACK;
    static final Color DEAD_CELL_BACKGROUND = Color.WHITE;

    private int numRows;
    private int numColumns;
    private ConwaysByWikus conwayModel;
    CellPanel[][] cellPanels;

    public ConwayGridPanel(ConwaysByWikus conwayModel) {
        this.numRows = conwayModel.NUM_ROWS;
        this.numColumns = conwayModel.NUM_COLUMNS;
        this.conwayModel = conwayModel;
        this.cellPanels = new CellPanel[numRows][numColumns];
        setLayout(new GridLayout(numRows, numColumns));
        createGridCells();
    }

    private void createGridCells() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                CellPanel cellPanel = new CellPanel(conwayModel.isAlive(new Point(row, col)));
                // Create cell borders
                if (row < numRows - 1) {
                    if (col < numColumns - 1) {
                        cellPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY));
                    } else {
                        cellPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.GRAY));
                    }
                } else {
                    if (col < numColumns - 1) {
                        cellPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY));
                    } else {
                        cellPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
                    }
                }
                add(cellPanel);
                cellPanels[row][col] = cellPanel;
            }
        }
    }

    void updateGridCells() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                cellPanels[row][col].setStatus(conwayModel.isAlive(new Point(row, col)));
            }
        }
    }

    public class CellPanel extends JPanel {

        public CellPanel(boolean isAlive) {
            setStatus(isAlive);
        }

        void setStatus(boolean isAlive) {
            if (isAlive) setBackground(LIVE_CELL_BACKGROUND);
            else setBackground(DEAD_CELL_BACKGROUND);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(80, 80);
        }
    }
}
