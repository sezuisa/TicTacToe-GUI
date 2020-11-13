package de.sarah.elements;

import javax.swing.*;
import java.awt.*;

public class Menu {

    JPanel menuPanel;

    // Constants
    protected final int MENU_WIDTH = 500;
    protected final int MENU_HEIGHT = 730;

    protected final ImageIcon X = new ImageIcon("src/images/X.png");
    protected final ImageIcon O = new ImageIcon("src/images/O.png");

    //protected String[] settings = new String[2];

    // JLabels - Title screen
    protected JLabel title;
    protected JLabel instructions;

    public Menu() {

    }

    protected JPanel createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menuPanel.setBackground(new Color(50, 50, 50));
        menuPanel.setMaximumSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menuPanel.setMinimumSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));

        title = new JLabel("TIC TAC TOE");
        title.setForeground(Color.white);
        title.setFont(new Font("Helvetica", Font.PLAIN, 50));
        //title.setHorizontalTextPosition(JLabel.CENTER);
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        instructions = new JLabel("<html><div style='text-align: center;'>" +
                "Choose a symbol and a<br>difficulty level to start</div><html>", JLabel.CENTER);
        instructions.setForeground(Color.white);
        instructions.setFont(new Font("Helvetica", Font.PLAIN, 20));
        instructions.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        menuPanel.add(Box.createRigidArea(new Dimension(0, 150)));
        menuPanel.add(title);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(instructions);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        return menuPanel;
    }

}
