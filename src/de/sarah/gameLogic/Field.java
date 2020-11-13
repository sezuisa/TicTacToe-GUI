package de.sarah.gameLogic;

import java.util.Arrays;
import java.util.stream.Stream;

public class Field {

    /*
     * |----------------------------------------------------|
     * |------------------THE Field-CLASS-------------------|
     * |----------------------------------------------------|
     * This class contains methods regarding the initial build of the game field,
     * printing out the field and updating it after each move.
     */

    String[][] playingField;
    int[] fieldCoordinates;
    long count;
    long countEmpty;
    boolean found2InRow = false;
    int index;

    /*
     * BUILDING THE INITIAL FIELD
     * The user input is checked for validity (it must contain exactly 9 symbols which are either
     * X, O or _). If the input is invalid, the user has to do another input. (1)
     * This input gets split up into an array (String[9]), separating the individual symbols. (2)
     * Two for-loops initialize the field with the values from this array, a separate counter serving to
     * access the correct indices in the array. (3)
     * The field is returned. (4)
     */
    public String[][] buildField(String config) {
//        if (!config.matches("[XO_]+") || config.length() != 9) { // 1
//            System.out.print("Invalid input! Try again: ");
//            config = scan.nextLine();
//            buildField(config);
//        }
        String[][] field = new String[3][3];

        String[] symbols = config.split(""); // 2

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (symbols[count].equals("_")) {
                    field[i][j] = " "; // "_" is saved as " " (space) in the playing field
                } else {
                    field[i][j] = symbols[count]; // 3
                }
                count++;
            }
        }

        return field; // 4
    }

    /*
     * PRINTING OUT THE FIELD
     * The field is printed out row by row; a border of "-" or "|" is added around
     * for aesthetic purposes.
     */
    public void printField() {
        System.out.println("---------");
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(playingField[0][i] + " ");
        }
        System.out.println("|");
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(playingField[1][i] + " ");
        }
        System.out.println("|");
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(playingField[2][i] + " ");
        }
        System.out.println("|");
        System.out.println("---------");
    }

    /*
     * UPDATING THE FIELD
     * Two counters determine the amount of X and O currently on the field. (1)
     * If the amount of X is higher than O, the next move is done with O. (2.1)
     * If the amounts of both symbols are the same, the next move is done with X. (2.2)
     * The empty cell (" ") at the given coordinates is then overwritten and the updated field
     * is printed by calling the printField() method. (3)
     */
    public void updateField(int[] coordinates, String symbol) {
//        int countX = 0;
//        int countO = 0;
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (playingField[i][j].equals("X")) {
//                    countX++;
//                } else if (playingField[i][j].equals("O")) {
//                    countO++;
//                }
//            }
//        }
//
//        if (countX > countO) {
//            playingField[coordinates[0]][coordinates[1]] = "O"; // 2.1
//        } else if (countX == countO) {
//            playingField[coordinates[0]][coordinates[1]] = "X"; // 2.2
//        }

        playingField[coordinates[0]][coordinates[1]] = symbol;
        printField(); // 3
    }

    /*
     * COUNTING SYMBOLS IN A ROW
     */
    public long countRowSymbols(String symbol, int index) {
        return Arrays.stream(playingField[index]).filter(c -> c.equals(symbol)).count();
    }

    /*
     * 2 IN A ROW
     * Checks if there are two same symbols and an empty cell in a row and returns
     * the coordinates of that empty cell.
     */
    public int[] setRowSymbols(String symbolToSearch) {
        int[] coordinates = new int[2];
        for (int i = 0; i < 3; i++) {
            if (countRowSymbols(symbolToSearch, i) == 2 &&
                    countRowSymbols(" ", i) == 1) {
                found2InRow = true;
                index = String.join("", playingField[i]).indexOf(" ");
                coordinates[0] = i;
                coordinates[1] = index;
                break;
            }
        }
        return coordinates;
    }

    /*
     * GETTING A COLUMN OF THE FIELD
     * Returns the column at index as an array.
     */
    public String[] getColumn(String[][] field, int index){
        String[] column = new String[3];
        for(int i = 0; i < 3; i++){
            column[i] = field[i][index];
        }
        return column;
    }

    /*
     * COUNTING SYMBOLS IN A COLUMN
     */
    public long countColumnSymbols(String[] column, String symbol) {
        return Arrays.stream(column).filter(c -> c.equals(symbol)).count();
    }

    /*
     * 2 IN A COLUMN
     * Checks if there are two same symbols and an empty cell in a column and returns
     * the coordinates of that empty cell.
     */
    public int[] setColumnSymbols(String symbolToSearch) {
        int[] coordinates = new int[2];
        for (int i = 0; i < 3; i++) {
            if (countColumnSymbols(getColumn(playingField, i), symbolToSearch) == 2 &&
                    countColumnSymbols(getColumn(playingField, i), " ") == 1) {
                found2InRow = true;
                index = String.join("", getColumn(playingField, i)).indexOf(" ");
                coordinates[0] = index;
                coordinates[1] = i;
                break;
            }
        }
        return coordinates;
    }

    /*
     * 2 IN DIAGONAL 1 (top left to bottom right)
     * Checks if there are two same symbols and an empty cell in the diagonal and returns
     * the coordinates of that empty cell.
     */
    public int[] setDiagonal1(String symbolToSearch) {
        int[] coordinates = new int[2];
        String[] diagonal = {playingField[0][0], playingField[1][1], playingField[2][2]};
        count = Arrays.stream(diagonal).filter(c -> c.equals(symbolToSearch)).count();
        countEmpty = Arrays.stream(diagonal).filter(c -> c.equals(" ")).count();
        if (count == 2 && countEmpty == 1) {
            found2InRow = true;
            index = String.join("", diagonal).indexOf(" ");
            coordinates[0] = index;
            coordinates[1] = index;
        }
        count = 0;

        return coordinates;
    }

    /*
     * 2 IN DIAGONAL 2 (top right to bottom left)
     * Checks if there are two same symbols and an empty cell in the diagonal and returns
     * the coordinates of that empty cell.
     */
    public int[] setDiagonal2(String symbolToSearch) {
        int[] coordinates = new int[2];
        String[] diagonal = {playingField[0][2], playingField[1][1], playingField[2][0]};
        count = Arrays.stream(diagonal).filter(c -> c.equals(symbolToSearch)).count();
        countEmpty = Arrays.stream(diagonal).filter(c -> c.equals(" ")).count();
        if (count == 2 && countEmpty == 1) {
            found2InRow = true;
            index = String.join("", diagonal).indexOf(" ");
            if (index == 0) {
                coordinates[1] = 2;
            } else if (index == 1) {
                coordinates[0] = index;
                coordinates[1] = 1;
            } else {
                coordinates[0] = index;
            }

        }
        count = 0;

        return coordinates;
    }

    public String[] fieldToArray(String[][] field) {
        return Stream.of(field)
                .flatMap(Stream::of)
                .toArray(String[]::new);
    }

//    public String[][] arrayToField(String[] arr) {
//        String[][] field = new String[3][3];
//        int count = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                field[i][j] = arr[count];
//                count++;
//            }
//        }
//        return field;
//    }


    public String[][] getPlayingField() {
        return playingField;
    }

    public void setPlayingField(String[][] playingField) {
        this.playingField = playingField;
    }

    public int[] getFieldCoordinates() {
        return fieldCoordinates;
    }

    public void setFieldCoordinates(int[] fieldCoordinates) {
        this.fieldCoordinates = fieldCoordinates;
    }
}
