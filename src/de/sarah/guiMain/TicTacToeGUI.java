package de.sarah.guiMain;

import de.sarah.elements.*;
import de.sarah.gameLogic.Field;
import de.sarah.gameLogic.Game;

import javax.swing.*;
import java.awt.*;

public class TicTacToeGUI extends JFrame {

    JPanel mainPanel, gamePane, menu1Pane, menu2Pane;

    Menu1 menu1;
    final static String MENU1_PANEL = "Choose Symbol";

    Menu2 menu2;
    final static String MENU2_PANEL = "Choose Difficulty";

    GameField fieldUI;
    final static String FIELD_PANEL = "Game";

    Game ticTacToe;
    Field field;
    int gameMode;

    public static boolean isFirstMove;
    public static boolean userHasMadeMove;


    public TicTacToeGUI() {
        //setSize(500, 670);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("TicTacToe");
        setLocationRelativeTo(null);
        //setUndecorated(true);
        //setBackground(new Color(50, 50, 50, 50));
        getContentPane().setBackground(new Color(50, 50, 50));

        CardLayout cardLayout = new CardLayout();

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(500, 730));
        mainPanel.setLayout(cardLayout);

        menu1 = new Menu1(cardLayout, mainPanel);
        menu1Pane = menu1.getMenuPanel();

        menu2 = new Menu2(cardLayout, mainPanel, menu1.getSettings());
        menu2Pane = menu2.getMenuPanel();

        fieldUI = new GameField();
        gamePane = fieldUI.getGamePane();

        mainPanel.add(menu1Pane, MENU1_PANEL);
        mainPanel.add(menu2Pane, MENU2_PANEL);
        mainPanel.add(gamePane, FIELD_PANEL);

        cardLayout.show(mainPanel, MENU1_PANEL);

        getContentPane().add(mainPanel);

        pack();
        setVisible(true);

        ticTacToe = new Game();
        field = new Field();

        //String[][] fieldArr = fieldUI.guiFieldToArray(fieldUI.getField());

    }

    public static void main(String[] args) {

        // I know Swing apps should be started with
        // EventQueue.invokeLater(), however I'm not quite sure how to implement that together
        // with starting the game logic/loop
        TicTacToeGUI game = new TicTacToeGUI();
        game.startGame();


    }

    public void startGame() {
        // Creating objects of all the required classes
        ticTacToe = new Game();
        field = new Field();

        while (!ticTacToe.isExitCommand()) {
            gameMode = ticTacToe.menu();

            if (gameMode != -1) {
                field.setPlayingField(field.buildField("_________"));
                field.printField();
            }

            ticTacToe.setPlayerTypes(gameMode);

            while (!ticTacToe.isTerminateGame()) {
                isFirstMove = true;
                userHasMadeMove = false;
                field.setFieldCoordinates(ticTacToe.makeMove(ticTacToe.getPlayer1(), ticTacToe, field));

                field.updateField(field.getFieldCoordinates(), ticTacToe.getPlayer1().getSymbol());
                ticTacToe.checkGameState(field);

                if (!ticTacToe.isTerminateGame()) {

                    if (ticTacToe.getPlayer2().isUser()) {
                        isFirstMove = true;
                        System.out.println("Yup, is User");
                    } else {
                        field.setFieldCoordinates(ticTacToe.makeMove(ticTacToe.getPlayer2(), ticTacToe, field));
                        field.updateField(field.getFieldCoordinates(), ticTacToe.getPlayer2().getSymbol());
                        fieldUI.updateGUIField(field.getFieldCoordinates());
                    }




                    ticTacToe.checkGameState(field);
                }
            }
            if (!ticTacToe.isExitCommand()) {
                System.out.println();
            }
        }
    }

}
