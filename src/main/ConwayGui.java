package main;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ConwayGui {

    final int MIN_GRID_SIZE = 3;
    final int MAX_GRID_SIZE = 50;
    final ScheduledExecutorService executor;
    ScheduledFuture<?> scheduledFuture;

    JFrame frame;
    ConwayGridPanel conwayGridPanel;
    JButton playButton = new JButton("Play");
    JButton nextButton = new JButton("Next");
    JButton clearButton = new JButton("Clear");
    JButton newGridButton = new JButton("New...");

    boolean paused = true;

    public ConwayGui(ConwaysByWikus conwayModel) {
        executor = Executors.newSingleThreadScheduledExecutor();
        createGui(conwayModel);
    }

    private void createGui(ConwaysByWikus conwayModel) {
        createFrame();
        createGrid(conwayModel);
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

    private void createGrid(ConwaysByWikus conwayModel) {
        conwayGridPanel = new ConwayGridPanel(conwayModel);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        frame.add(conwayGridPanel, constraints);
    }

    private void createButtons() {
        GridBagConstraints constraints = new GridBagConstraints();
        // Add play button
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
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
                scheduledFuture = executor.scheduleWithFixedDelay(conwayGridPanel::showNextStep, 0, 500,
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
        nextButton.addActionListener(actionEvent -> conwayGridPanel.showNextStep());
        // Add clear button
        constraints.gridx = 2;
        constraints.gridy = 1;
        frame.add(clearButton, constraints);
        clearButton.addActionListener(actionEvent -> conwayGridPanel.clearGrid());
        // Add new grid button
        constraints.gridx = 3;
        constraints.gridy = 1;
        frame.add(newGridButton, constraints);
        newGridButton.addActionListener(actionEvent -> showNewGridDialog());
    }

    private void showNewGridDialog() {
        JSpinner rowSpinner = new JSpinner(new SpinnerNumberModel(3, MIN_GRID_SIZE, MAX_GRID_SIZE, 1));
        JSpinner columnSpinner = new JSpinner(new SpinnerNumberModel(3, MIN_GRID_SIZE, MAX_GRID_SIZE, 1));
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Rows:"));
        panel.add(rowSpinner);
        panel.add(new JLabel("Columns:"));
        panel.add(columnSpinner);
        int result = JOptionPane.showConfirmDialog(null, panel, "Create Grid",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            conwayGridPanel.changeConwayModel((int) rowSpinner.getValue(), (int) columnSpinner.getValue());
        }
    }
}
