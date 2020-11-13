package de.sarah.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu1 extends Menu {

    // Main panel (with CardLayout)
    private JPanel mainPanel;
    CardLayout cards;

    // Menupanel
    private JPanel menuPanel;

    // Playable symbols to choose
    private SymbolBar symbols;
    private JPanel symbolsPanel;

    private String[] settings;

    // Constructor
    public Menu1(final CardLayout cardLayout, final JPanel panel) {
        mainPanel = panel;
        cards = cardLayout;
        menuPanel = createMenuPanel();

        settings = new String[2];

        symbols = new SymbolBar();
        symbols.getSymbolBar().setAlignmentX(JPanel.CENTER_ALIGNMENT);
        symbols.getPlaceHolderX().addMouseListener(new MenuSymbolListener());
        symbols.getPlaceHolderO().addMouseListener(new MenuSymbolListener());

        menuPanel.add(symbols.getSymbolBar());

    }

    // Nested Class
    private class MenuSymbolListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            Component symbol = mouseEvent.getComponent();
            if (symbol == symbols.getPlaceHolderX()) {
                settings[0] = "X";
            } else if (symbol == symbols.getPlaceHolderO()) {
                settings[0] = "O";
            }
            //System.out.println(settings[0]);
            cards.show(mainPanel, "Choose Difficulty");
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

    public String[] getSettings() { return settings; }
}
