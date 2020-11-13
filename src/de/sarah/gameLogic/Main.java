package de.sarah.gameLogic;

public class Main {

    public static void main(String[] args) {

        // Creating objects of all the required classes
        Game ticTacToe = new Game();
        Field field = new Field();

        while (!ticTacToe.exitCommand) {
            int gameMode = ticTacToe.menu();

            if (gameMode != -1) {
                field.playingField = field.buildField("_________");
                field.printField();
            }

            ticTacToe.setPlayerTypes(gameMode);

            while (!ticTacToe.terminateGame) {
                field.fieldCoordinates = ticTacToe.makeMove(ticTacToe.getPlayer1(), ticTacToe, field);

                field.updateField(field.fieldCoordinates, ticTacToe.getPlayer1().symbol);
                ticTacToe.checkGameState(field);

                if (!ticTacToe.terminateGame) {
                    field.fieldCoordinates = ticTacToe.makeMove(ticTacToe.getPlayer2(), ticTacToe, field);
                    field.updateField(field.fieldCoordinates, ticTacToe.getPlayer2().symbol);
                    ticTacToe.checkGameState(field);
                }
            }
            if (!ticTacToe.exitCommand) {
                System.out.println();
            }
        }
    }
}
