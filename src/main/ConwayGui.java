package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        frame.add(conwayGridPanel, constraints);
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
