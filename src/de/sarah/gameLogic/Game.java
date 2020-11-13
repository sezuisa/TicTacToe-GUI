package de.sarah.gameLogic;

import de.sarah.elements.Menu2;

import java.util.Scanner;

public class Game {

    /*
     * |----------------------------------------------------|
     * |------------------THE GAME-CLASS--------------------|
     * |----------------------------------------------------|
     * This class contains methods regarding the initial setup of the game mode, player types
     * making a move in the game and checking the game state after each move.
     */

    boolean terminateGame;
    boolean exitCommand;
    int gameMode;
    private Player player1;
    private Player player2;

    /*
     * LETTING THE USER CHOOSE THE GAME MODE
     * The user input is validated: It must contain either just the word "exit", which leads to
     * the termination of the game (1), or it must contain the word "start" followed by two
     * parameters defining the player types. (2)
     * Those parameters are checked and the variable gameMode is initialized accordingly.
     * GAME MODES:
     * 0 - Two easy computers (3)
     * 1 - User against user (4)
     * 2 - User against easy computer (5)
     * 3 - Easy computer against user (6)
     * -1 - Invalid command (7)
     * In case of invalid input, the user is asked to perform another input via recursion of
     * the method menu().
     * The game mode is then returned. (8)
     */
    public int menu() {
        terminateGame = false;
        exitCommand = false;
        gameMode = -1;

        String[] command = Menu2.getSettings();

        if ("X".equals(command[0]) && "user".equals(command[1])) { // 3
            gameMode = 0;
        } else if ("X".equals(command[0]) && "easy".equals(command[1])) { // 4
            gameMode = 1;
        } else if ("X".equals(command[0]) && "medium".equals(command[1])) { // 5
            gameMode = 2;
        } else if ("X".equals(command[0]) && "hard".equals(command[1])) { // 6
            gameMode = 3;
        } else if ("O".equals(command[0]) && "user".equals(command[1])) {
            gameMode = 4;
        } else if ("O".equals(command[0]) && "easy".equals(command[1])) {
            gameMode = 5;
        } else if ("O".equals(command[0]) && "medium".equals(command[1])) {
            gameMode = 6;
        } else if ("O".equals(command[0]) && "hard".equals(command[1])) {
            gameMode = 7;
        }
        return gameMode; // 8
    }

    /*
     * SETTING THE PLAYER TYPES
     * The method takes one parameter, which is the before determined game mode returned by the
     * method menu().
     * Depending on the game mode, player1 and player2 are initialized as different objects.
     */
    public void setPlayerTypes(int gameMode) {
        switch (gameMode) {
            case 0:
                player1 = new User("X", true);
                player2 = new User("O", true);
                break;
            case 1:
                player1 = new User("X", true);
                player2 = new Computer("O", false, "easy");
                break;
            case 2:
                player1 = new User("X", true);
                player2 = new Computer("O", false, "medium");
                break;
            case 3:
                player1 = new User("X", true);
                player2 = new Computer("O", false, "hard");
                break;
            case 4:
                player1 = new User("O", true);
                player2 = new User("X", true);
                break;
            case 5:
                player1 = new User("O", true);
                player2 = new Computer("X", false, "easy");
                break;
            case 6:
                player1 = new User("O", true);
                player2 = new Computer("X", false, "medium");
                break;
            case 7:
                player1 = new User("O", true);
                player2 = new Computer("X", false, "hard");
                break;
            default:
                terminateGame = true;
                break;
        }
    }


    /*
     * MAKING A MOVE ON THE FIELD
     * This method takes three parameters: An Player-object, a Game-object and
     * a Field-object.
     */
    public int[] makeMove(Player player, Game game, Field field) {
        return player.makePlayerMove(game, field);
    }

    /*
     * CHECKING THE GAME STATE
     * The field is traversed row by row.
     * If a row is taken up by one symbol only, that symbol has won the game. (won = true)
     * If won = false and an empty cell is found, the game is not finished.
     * If won = false and no empty cell is found, it's a draw.
     */
    public void checkGameState(Field field) {
        boolean won = false;
        int countEmpty = 0, countX, countO;
        for (int i = 0; i < 3; i++) {
            countX = 0;
            countO = 0;
            for (int j = 0; j < 3; j++) {
                if (field.playingField[i][j].equals("X")) {
                    countX++;
                } else if (field.playingField[i][j].equals("O")) {
                    countO++;
                } else {
                    countEmpty++;
                }
            }
            if (countX == 3) {
                won = true;
                System.out.println("X wins");
                terminateGame = true;
                break;
            } else if (countO == 3) {
                won = true;
                System.out.println("O wins");
                terminateGame = true;
                break;
            } else if (field.playingField[0][0].equals("X") && field.playingField[1][1].equals("X") &&
                    field.playingField[2][2].equals("X")) {
                won = true;
                System.out.println("X wins");
                terminateGame = true;
                break;
            } else if (field.playingField[0][0].equals("O") && field.playingField[1][1].equals("O") &&
                    field.playingField[2][2].equals("O")) {
                won = true;
                System.out.println("O wins");
                terminateGame = true;
                break;
            } else if (field.playingField[0][2].equals("X") && field.playingField[1][1].equals("X") &&
                    field.playingField[2][0].equals("X")) {
                won = true;
                System.out.println("X wins");
                terminateGame = true;
                break;
            } else if (field.playingField[0][2].equals("O") && field.playingField[1][1].equals("O") &&
                    field.playingField[2][0].equals("O")) {
                won = true;
                System.out.println("O wins");
                terminateGame = true;
                break;
            }
        }

        if (!won) {
            for (int i = 0; i < 3; i++) {
                countX = 0;
                countO = 0;
                for (int j = 0; j < 3; j++) {
                    if (field.playingField[j][i].equals("X")) {
                        countX++;
                    } else if (field.playingField[j][i].equals("O")) {
                        countO++;
                    }
                }
                if (countX == 3) {
                    won = true;
                    System.out.println("X wins");
                    terminateGame = true;
                    break;
                } else if (countO == 3) {
                    won = true;
                    System.out.println("O wins");
                    terminateGame = true;
                    break;
                }
            }
        }

        if (!won && countEmpty > 0) {
            terminateGame = false;
        } else if (!won && countEmpty == 0) {
            System.out.println("Draw");
            terminateGame = true;
        }
    }

    /*
     * Getters and Setters
     */
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isTerminateGame() {
        return terminateGame;
    }

    public void setTerminateGame(boolean terminateGame) {
        this.terminateGame = terminateGame;
    }

    public boolean isExitCommand() {
        return exitCommand;
    }

    public void setExitCommand(boolean exitCommand) {
        this.exitCommand = exitCommand;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
}
