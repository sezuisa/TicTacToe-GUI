package de.sarah.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu2 extends Menu {

    // Main panel (with CardLayout)
    private JPanel mainPanel;
    CardLayout cards;

    // JPanel
    private JPanel menuPanel;

    // Difficulty settings to choose
    private DifficultyBar difficultyBar;

    private static String[] settings;

    // Constructor
    public Menu2(final CardLayout cardLayout, final JPanel panel, String[] settings) {
        mainPanel = panel;
        cards = cardLayout;

        Menu2.settings = settings;

        menuPanel = createMenuPanel();

        difficultyBar = new DifficultyBar();
        difficultyBar.getDifficultyBar().setAlignmentX(JPanel.CENTER_ALIGNMENT);
        difficultyBar.getUserDifficulty().addMouseListener(new MenuDifficultyListener());
        difficultyBar.getEasyDifficulty().addMouseListener(new MenuDifficultyListener());
        difficultyBar.getMediumDifficulty().addMouseListener(new MenuDifficultyListener());
        difficultyBar.getHardDifficulty().addMouseListener(new MenuDifficultyListener());

        menuPanel.add(difficultyBar.getDifficultyBar());
    }

    // Nested Class
    private class MenuDifficultyListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Component difficulty = mouseEvent.getComponent();
            if (difficulty == difficultyBar.getUserDifficulty()) {
                settings[1] = "user";
            } else if (difficulty == difficultyBar.getEasyDifficulty()) {
                settings[1] = "easy";
            } else if (difficulty == difficultyBar.getMediumDifficulty()) {
                settings[1] = "medium";
            } else if (difficulty == difficultyBar.getHardDifficulty()) {
                settings[1] = "hard";
            }
            //System.out.println(settings[1]);
            //System.out.println(settings[0]);
            cards.show(mainPanel, "Game");
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {}

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public static String[] getSettings() { return settings; }

}
