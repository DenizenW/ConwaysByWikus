package main;

import javax.swing.*;
import java.awt.*;

public class ConwayGui {

    public ConwayGui(ConwaysByWikus conwayModel) {
        JFrame frame = new JFrame("Conway's by Wikus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new ConwayGridPanel(conwayModel));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
