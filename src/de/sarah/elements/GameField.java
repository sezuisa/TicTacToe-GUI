package de.sarah.elements;

import de.sarah.guiMain.TicTacToeGUI;
import de.sarah.transferHandler.ImageSelection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class GameField {

    private JPanel gamePane;
    private JPanel field;

    private final int FIELD_WIDTH = 500;
    private final int FIELD_HEIGHT = 500;

    private final ImageIcon X = new ImageIcon("src/images/X.png");
    private final ImageIcon O = new ImageIcon("src/images/O.png");

    private SymbolBar symbolBar;
    private JLabel separator;

    // Row 1
    private JLabel r1c1;
    private JLabel r1c2;
    private JLabel r1c3;

    // Row 2
    private JLabel r2c1;
    private JLabel r2c2;
    private JLabel r2c3;

    // Row 3
    private JLabel r3c1;
    private JLabel r3c2;
    private JLabel r3c3;

    private JLabel[][] cells;

    private JButton endMoveButton;

    public GameField() {
        gamePane = createPane();

        field = new JPanel();
        field.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        field.setLayout(new GridLayout(3, 3));
        field.setBackground(new Color(50, 50, 50));

        r1c1 = createCellLight();
        r1c2 = createCellDark();
        r1c3 = createCellLight();

        r2c1 = createCellDark();
        r2c2 = createCellLight();
        r2c3 = createCellDark();

        r3c1 = createCellLight();
        r3c2 = createCellDark();
        r3c3 = createCellLight();

        field.add(r1c1);
        field.add(r1c2);
        field.add(r1c3);

        field.add(r2c1);
        field.add(r2c2);
        field.add(r2c3);

        field.add(r3c1);
        field.add(r3c2);
        field.add(r3c3);

        endMoveButton = new JButton("End Move");
        endMoveButton.setPreferredSize(new Dimension(100, 50));
        setEndMoveButtonListener();

        symbolBar = new SymbolBar(endMoveButton);
        separator = new JLabel();

        symbolBar.getSymbolBar().setBackground(new Color(255, 255, 255, 30));

        separator.setPreferredSize(new Dimension(500, 10));
        separator.setOpaque(true);
        separator.setBackground(new Color(255, 255, 255, 50));


        setTransferHandlers();
        setCells();


        gamePane.add(field);
        gamePane.add(separator);
        gamePane.add(symbolBar.getSymbolBar());
    }

    private JLabel createCellLight() {
        JLabel label = new JLabel();
        label.setBackground(new Color(60, 60, 60));
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        return label;
    }

    private JLabel createCellDark() {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        return label;
    }

    private void setXIcon(JLabel cell) {
        cell.setIcon(X);
        cell.setHorizontalAlignment(JLabel.CENTER);
        cell.setVerticalAlignment(JLabel.CENTER);
    }

    private void setOIcon(JLabel cell) {
        cell.setIcon(O);
        cell.setHorizontalAlignment(JLabel.CENTER);
        cell.setVerticalAlignment(JLabel.CENTER);
    }

    private JPanel createPane() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 730));
        panel.setBackground(new Color(50, 50, 50));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        return panel;
    }

    private void setTransferHandlers() {
        r1c1.setTransferHandler(new ImageSelection());
        r1c2.setTransferHandler(new ImageSelection());
        r1c3.setTransferHandler(new ImageSelection());

        r2c1.setTransferHandler(new ImageSelection());
        r2c2.setTransferHandler(new ImageSelection());
        r2c3.setTransferHandler(new ImageSelection());

        r3c1.setTransferHandler(new ImageSelection());
        r3c2.setTransferHandler(new ImageSelection());
        r3c3.setTransferHandler(new ImageSelection());

        symbolBar.getPlaceHolderX().setTransferHandler(new ImageSelection());
        symbolBar.getPlaceHolderO().setTransferHandler(new ImageSelection());

        DragMouseAdapter dragMouseAdapter = new DragMouseAdapter();
        symbolBar.getPlaceHolderX().addMouseListener(dragMouseAdapter);
        symbolBar.getPlaceHolderO().addMouseListener(dragMouseAdapter);

    }

    private void setEndMoveButtonListener() {
        endMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*
                 * if (userHasMadeMove && moveCount == 1) {
                 *      move beendet, moveCount = 0, Gegner ist dran
                 * } else if (userHasMadeMove && moveCount != 1) {
                 *      Message: You can only make one move at a time!
                 * } else if (!userHasMadeMove) {
                 *      Message: You have to make a move first!
                 * }
                 */

                if (TicTacToeGUI.userHasMadeMove) {

                } else {
                    JOptionPane.showMessageDialog(gamePane, "Warnung Text", "Warnung", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }


    private class DragMouseAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            JComponent jComponent = (JComponent)e.getSource();
            TransferHandler handler = jComponent.getTransferHandler();

            if (TicTacToeGUI.isFirstMove) {
                handler.exportAsDrag(jComponent, e, TransferHandler.COPY);
            }

        }
    }

    public String[][] guiFieldToArray(JPanel guiField) {
        String[][] arr = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getIcon() == null) {
                    arr[i][j] = " ";
                } else if (cells[i][j].getIcon() == X) {
                    arr[i][j] = "X";
                } else if (cells[i][j].getIcon() == O) {
                    arr[i][j] = "O";
                }
            }
        }

        for (String[] rows : arr) {
            System.out.println(Arrays.toString(rows));
        }

        return arr;
    }

    public void updateGUIField(int[] coordinates) {
        setOIcon(cells[coordinates[0]][coordinates[1]]);
    }

    /* -----------------------------------------------------------------------------------------------
     * Getters and Setters
     * -----------------------------------------------------------------------------------------------
     */

    public JPanel getField() {
        return field;
    }

    public void setField(JPanel field) {
        this.field = field;
    }

    public JPanel getGamePane() {
        return gamePane;
    }

    public void setGamePane(JPanel gamePane) {
        this.gamePane = gamePane;
    }

    public void setCells() {
        cells = new JLabel[][]{
                {r1c1, r1c2, r1c3},
                {r2c1, r2c2, r2c3},
                {r3c1, r3c2, r3c3}
        };
    }

    public JLabel[][] getCells() {
        return cells;
    }

}
