package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConwayGridPanel extends JPanel {

    static final Color LIVE_CELL_BACKGROUND = Color.BLACK;
    static final Color DEAD_CELL_BACKGROUND = Color.WHITE;

    private ConwaysByWikus conwayModel;
    CellPanel[][] cellPanels;
    boolean processMouseEvents = true;

    public ConwayGridPanel(ConwaysByWikus conwayModel) {
        this.conwayModel = conwayModel;
        setLayout(new GridLayout(conwayModel.NUM_ROWS, conwayModel.NUM_COLUMNS));
        createGridCells();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    private void createGridCells() {
        cellPanels = new CellPanel[conwayModel.NUM_ROWS][conwayModel.NUM_COLUMNS];
        for (int row = 0; row < conwayModel.NUM_ROWS; row++) {
            for (int col = 0; col < conwayModel.NUM_COLUMNS; col++) {
                CellPanel cellPanel = new CellPanel(row, col, conwayModel.isAlive(row, col), this);
                // Create cell borders
                if (row < conwayModel.NUM_ROWS - 1) {
                    if (col < conwayModel.NUM_COLUMNS - 1) {
                        cellPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY));
                    } else {
                        cellPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.GRAY));
                    }
                } else {
                    if (col < conwayModel.NUM_COLUMNS - 1) {
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

    void showNextStep() {
        conwayModel.updateGridState();
        updateGridCells();
    }

    void changeConwayModel(int rows, int columns) {
        conwayModel = new ConwaysByWikus(rows, columns);
        removeAll();
        setLayout(new GridLayout(conwayModel.NUM_ROWS, conwayModel.NUM_COLUMNS));
        createGridCells();
        validate();
        repaint();
    }

    void updateGridCells() {
        for (int row = 0; row < conwayModel.NUM_ROWS; row++) {
            for (int col = 0; col < conwayModel.NUM_COLUMNS; col++) {
                cellPanels[row][col].setStatus(conwayModel.isAlive(row, col));
            }
        }
    }

    void clearGrid() {
        conwayModel.clearGrid();
        updateGridCells();
    }

    public class CellPanel extends JPanel {

        final int ROW;
        final int COLUMN;

        public CellPanel(int row, int column, boolean isAlive, ConwayGridPanel gridPanel) {
            ROW = row;
            COLUMN = column;
            setStatus(isAlive);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (gridPanel.processMouseEvents) {
                        // Process event if it originated from a point within this cell
                        Point p = e.getPoint();
                        if (contains(p.x, p.y)) {
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                conwayModel.setCellState(ROW, COLUMN, true);
                            } else if (SwingUtilities.isRightMouseButton(e)) {
                                conwayModel.setCellState(ROW, COLUMN, false);
                            }
                            setStatus(conwayModel.isAlive(ROW, COLUMN));
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (gridPanel.processMouseEvents) {
                        boolean leftMouseDown = SwingUtilities.isLeftMouseButton(e);
                        boolean rightMouseDown = SwingUtilities.isRightMouseButton(e);
                        // Turn cell on if LMB is down but not RMB
                        if (leftMouseDown && !rightMouseDown) {
                            conwayModel.setCellState(ROW, COLUMN, true);
                            setStatus(conwayModel.isAlive(ROW, COLUMN));
                        }
                        // Turn cell off if RMB is down but not LMB
                        if (!leftMouseDown && rightMouseDown) {
                            conwayModel.setCellState(ROW, COLUMN, false);
                            setStatus(conwayModel.isAlive(ROW, COLUMN));
                        }
                    }
                }
            });
        }

        void setStatus(boolean isAlive) {
            if (isAlive) setBackground(LIVE_CELL_BACKGROUND);
            else setBackground(DEAD_CELL_BACKGROUND);
        }
    }
}
