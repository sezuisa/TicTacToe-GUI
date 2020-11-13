package de.sarah.elements;

import javax.swing.*;
import java.awt.*;

public class SymbolBar {

    private JPanel symbolBar;

    private final int BAR_WIDTH = 500;
    private final int BAR_HEIGHT = 230;

    private final ImageIcon X = new ImageIcon("src/images/X.png");
    private final ImageIcon O = new ImageIcon("src/images/O.png");

    private JLabel placeHolderX;
    private JLabel placeHolderO;

    private JLabel separator;

    public SymbolBar() {
        symbolBar = new JPanel();
        symbolBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
        symbolBar.setBackground(new Color(50, 50, 50));
        symbolBar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        placeHolderX = new JLabel();
        placeHolderX.setPreferredSize(new Dimension(140, 140));
        placeHolderX.setIcon(X);

        placeHolderO = new JLabel();
        placeHolderO.setPreferredSize(new Dimension(140, 140));
        placeHolderO.setIcon(O);

        symbolBar.add(placeHolderX);
        symbolBar.add(placeHolderO);

    }

    public SymbolBar(JButton button) {
        symbolBar = new JPanel();
        symbolBar.setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
        symbolBar.setBackground(new Color(50, 50, 50));
        symbolBar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        placeHolderX = new JLabel();
        placeHolderX.setPreferredSize(new Dimension(140, 140));
        placeHolderX.setIcon(X);

        placeHolderO = new JLabel();
        placeHolderO.setPreferredSize(new Dimension(140, 140));
        placeHolderO.setIcon(O);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(500, 80));
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(50, 50, 50));
        buttonPanel.add(button, new GridBagConstraints());

        separator = new JLabel();

        separator.setPreferredSize(new Dimension(500, 10));
        separator.setOpaque(true);
        separator.setBackground(new Color(40, 40, 40));

        symbolBar.add(placeHolderX);
        symbolBar.add(placeHolderO);
        symbolBar.add(separator);
        symbolBar.add(buttonPanel);

    }

    public JPanel getSymbolBar() {
        return symbolBar;
    }

    public void setSymbolBar(JPanel symbolBar) {
        this.symbolBar = symbolBar;
    }

    public JLabel getPlaceHolderX() {
        return placeHolderX;
    }

    public void setPlaceHolderX(JLabel placeHolderX) {
        this.placeHolderX = placeHolderX;
    }

    public JLabel getPlaceHolderO() {
        return placeHolderO;
    }

    public void setPlaceHolderO(JLabel placeHolderO) {
        this.placeHolderO = placeHolderO;
    }
}
