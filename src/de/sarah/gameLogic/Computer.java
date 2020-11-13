package de.sarah.gameLogic;

import java.util.Random;

public class Computer extends Player {

    /*
     * |----------------------------------------------------|
     * |-----------------THE COMPUTER-CLASS-----------------|
     * |----------------------------------------------------|
     * This class represents the computer as an automated opponent player in the game.
     * The computer can make moves with different difficulty settings:
     * easy (random moves)
     * medium
     * hard (unbeatable)
     */

    int randomX;
    int randomY;

    int[] medium;

    String difficulty;

    public Computer(String symbol, boolean isUser, String difficulty) {
        super(symbol, isUser);
        this.difficulty = difficulty;
    }

    @Override
    public int[] makePlayerMove(Game game, Field field) {
        if ("easy".equals(difficulty)) {
            return computerMoveRandom(field, "easy");
        } else if ("medium".equals(difficulty)) {
            return computerMoveMedium(field);
        }
        return computerMoveHard(field);
    }

    /*
     * |-----------------------------------------|
     * |----- E A S Y   D I F F I C U L T Y -----|
     * |-----------------------------------------|
     * LETTING THE COMPUTER MAKE A RANDOM MOVE
     * Two random values between 1 and 3 are generated and returned in an array
     * as the field coordinates.
     */
    public int[] computerMoveRandom(Field field, String difficulty) {
        randomX = 0;
        randomY = 0;
        Random randomCoordinates = new Random();
        randomX = randomCoordinates.nextInt(3);
        randomY = randomCoordinates.nextInt(3);

        if (!field.playingField[randomY][randomX].equals(" ")) {
            computerMoveRandom(field, difficulty);
        } else {
            System.out.println("Making move level \"" + difficulty + "\"");
        }
        return new int[] {randomY, randomX};
    }

    /*
     * |---------------------------------------------|
     * |----- M E D I U M   D I F F I C U L T Y -----|
     * |---------------------------------------------|
     * WIN, PREVENT WIN, RANDOM
     * If the computer can win in one move (two in a row), it makes a move to win.
     * If the opponent can win in one move, it makes a move to prevent that.
     * Otherwise, it makes a random move.
     */
    public int[] computerMoveMedium(Field field) {
        field.found2InRow = false;
        String otherSymbol = "X".equals(symbol) ? "O" : "X";

        // Checking rows for own Symbol
        medium = field.setRowSymbols(symbol);

        // Checking columns for own Symbol
        if (!field.found2InRow) medium = field.setColumnSymbols(symbol);

        // Checking rows for opponent Symbol
        if (!field.found2InRow) medium = field.setRowSymbols(otherSymbol);

        // Checking columns for opponent Symbol
        if (!field.found2InRow) medium = field.setColumnSymbols(otherSymbol);

        // Checking diagonal 1 for own Symbol
        if (!field.found2InRow) medium = field.setDiagonal1(symbol);

        // Checking diagonal 2 for own Symbol
        if (!field.found2InRow) medium = field.setDiagonal2(symbol);

        // Checking diagonal 1 for opponent Symbol
        if (!field.found2InRow) medium = field.setDiagonal1(otherSymbol);

        // Checking diagonal 2 for opponent Symbol
        if (!field.found2InRow) medium = field.setDiagonal2(otherSymbol);

        if (!field.found2InRow) {
            medium = computerMoveRandom(field, "medium");
        } else {
            System.out.println("Making move level \"medium\"");
        }
        return medium;
    }

    /*
     * |---------------------------------------------|
     * |------- H A R D   D I F F I C U L T Y -------|
     * |---------------------------------------------|
     * This AI is unbeatable. It implements the Minimax-Algorithm
     * to anticipate moves and acts accordingly. Playing against it
     * results in either losing or a draw.
     */
    public int[] computerMoveHard(Field field) {
        String aiPlayer = symbol;
        String huPlayer = "X".equals(symbol) ? "O" : "X";
        Minimax minimax = new Minimax();

        String[] origField = field.fieldToArray(field.playingField);

        MinimaxMove move = minimax.findBestMove(origField, huPlayer, aiPlayer, true);

        int index = move.getIndex();
        int[] coordinates = new int[2];
        // index zu 2d-Array-Koordinaten umrechnen
        if (index <= 2) {
            coordinates[1] = index;
        } else if (index <= 5) {
            coordinates[0] = 1;
            coordinates[1] = index - 3;
        } else {
            coordinates[0] = 2;
            coordinates[1] = index - 6;
        }
        System.out.println("Making move level \"hard\"");
        return coordinates;
    }

}
