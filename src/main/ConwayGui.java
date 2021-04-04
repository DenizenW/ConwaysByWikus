package main;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ConwayGui {

    ConwaysByWikus conwayModel;
    final ScheduledExecutorService executorService;

    ConwayGridPanel conwayGridPanel;
//    JButton playButton = new JButton("Play");
    JButton nextButton = new JButton("Next");

    public ConwayGui(ConwaysByWikus conwayModel) {
        this.conwayModel = conwayModel;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.conwayGridPanel = new ConwayGridPanel(conwayModel);

        JFrame frame = new JFrame("Conway's by Wikus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Add grid panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        frame.add(conwayGridPanel, constraints);
        // Add next button
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        frame.add(nextButton, constraints);
        nextButton.addActionListener(actionEvent -> simulationStep());

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void simulationStep() {
        conwayModel.step();
        conwayGridPanel.updateGridCells();
    }
}
