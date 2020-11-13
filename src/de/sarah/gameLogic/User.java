package de.sarah.gameLogic;

import java.util.Scanner;

public class User extends Player {

    /*
     * |----------------------------------------------------|
     * |------------------THE USER-CLASS--------------------|
     * |----------------------------------------------------|
     * This class represents the User as a player in the game. It handles player
     * input to make a move in the game.
     */

    Scanner scan = new Scanner(System.in);



    int inputX;
    int inputY;

    public User(String symbol, boolean isUser) {
        super(symbol, isUser);
    }

    /*
     * PROCESSING INPUT COORDINATES
     * User input is scanned. (1)
     * The player input must consist of two numbers. (2)
     * The numbers must represent valid coordinates of the playing field, meaning
     * they have to be 1 <= x <= 3 and 1 <= y <= 3 respectively. (3)
     *
     * The coordinates are then converted to the indices of the playing field matrix. (4)
     * Another validation takes place: The coordinates must point to an empty field. Only
     * then can the move be made, else the player is asked to input new coordinates. (5)
     * If both validations are successful, the coordinates (converted to the correct indices
     * on the field) are returned in an array. (6)
     */
    @Override
    public int[] makePlayerMove(Game game, Field field) {

        /*
         * Veränderungen für GUI:
         * - Scanner entfernen
         * - Keine Inputvalidierung mehr
         * -
         */

        inputX = 0;
        inputY = 0;
        String userInput;

        System.out.print("Enter the coordinates: ");
        userInput = scan.nextLine(); // 1
        String[] c = userInput.split(" ");

        boolean onlyNumbers = true;

        for (String s : c) {
            if (!s.matches("^[0-9]*$")) { // 2
                onlyNumbers = false;
                break;
            }
        }

        if (!onlyNumbers) {
            System.out.println("You should enter numbers!");
            makePlayerMove(game, field);
        } else {
            inputX = Integer.parseInt(c[0]);
            inputY = Integer.parseInt(c[1]);

            if ((inputX < 1 || inputX > 3) || (inputY < 1 || inputY > 3)) {
                System.out.println("Coordinates should be from 1 to 3!"); // 3
                makePlayerMove(game, field);
            } else {
                if (inputY == 1) { // 4
                    inputX--;
                    inputY++;
                } else if (inputY == 3) {
                    inputX--;
                    inputY -= 3;
                } else {
                    inputX--;
                    inputY--;
                }

                if (!field.playingField[inputY][inputX].equals(" ")) {
                    System.out.println("This cell is occupied! Choose another one!"); // 5
                    makePlayerMove(game, field);
                }
            }
        }

        return new int[] {inputY, inputX}; // 6
    }

}
