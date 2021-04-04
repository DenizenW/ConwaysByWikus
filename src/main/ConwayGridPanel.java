package main;

import javax.swing.*;
import java.awt.*;

public class ConwayGridPanel extends JPanel {

    private int numRows;
    private int numColumns;

    public ConwayGridPanel(int rows, int columns) {
        this.numRows = rows;
        this.numColumns = columns;
        setLayout(new GridLayout(numRows, numColumns));
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                JPanel cellPanel = new JPanel();
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
                cellPanel.setPreferredSize(new Dimension(80, 80));
                add(cellPanel);
            }
        }
    }
}
