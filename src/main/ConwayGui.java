package main;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ConwayGui {

    final ScheduledExecutorService executor;
    ScheduledFuture<?> scheduledFuture;
    ConwaysByWikus conwayModel;

    JFrame frame;
    ConwayGridPanel conwayGridPanel;
    JButton playButton = new JButton("Play");
    JButton nextButton = new JButton("Next");
    JButton clearButton = new JButton("Clear");
    JButton newGridButton = new JButton("New...");

    boolean paused = true;

    public ConwayGui(ConwaysByWikus conwayModel) {
        this.conwayModel = conwayModel;
        executor = Executors.newSingleThreadScheduledExecutor();
        createGui();
    }

    private void createGui() {
        createFrame();
        createGrid();
        createButtons();
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame("Conway's by Wikus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
    }

    private void createGrid() {
        conwayGridPanel = new ConwayGridPanel(conwayModel);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        frame.add(conwayGridPanel, constraints);
    }

    private void createButtons() {
        GridBagConstraints constraints = new GridBagConstraints();
        // Add play button
        constraints.gridx = 0;
        constraints.gridy = 1;
        frame.add(playButton, constraints);
        playButton.addActionListener(actionEvent -> {
            if (paused) {
                playButton.setText("Pause");
                nextButton.setEnabled(false);
                clearButton.setEnabled(false);
                newGridButton.setEnabled(false);
                conwayGridPanel.processMouseEvents = false;
                scheduledFuture = executor.scheduleWithFixedDelay(ConwayGui.this::simulationStep, 0, 500,
                        TimeUnit.MILLISECONDS);
            } else {
                scheduledFuture.cancel(false);
                playButton.setText("Play");
                nextButton.setEnabled(true);
                clearButton.setEnabled(true);
                newGridButton.setEnabled(true);
                conwayGridPanel.processMouseEvents = true;
            }
            paused = !paused;
        });
        // Add next button
        constraints.gridx = 1;
        constraints.gridy = 1;
        frame.add(nextButton, constraints);
        nextButton.addActionListener(actionEvent -> simulationStep());
        // Add clear button
        constraints.gridx = 2;
        constraints.gridy = 1;
        frame.add(clearButton, constraints);
        clearButton.addActionListener(actionEvent -> conwayGridPanel.clearGrid());
        // Add new grid button
        constraints.gridx = 3;
        constraints.gridy = 1;
        frame.add(newGridButton, constraints);
//        newGridButton.addActionListener(actionEvent -> conwayGridPanel.clearGrid());
    }

    private void simulationStep() {
        conwayModel.step();
        conwayGridPanel.updateGridCells();
    }
}
