package de.sarah.gameLogic;

import java.util.ArrayList;

public class Minimax {

    public int[] emptyIndices(String[] field) {
        ArrayList<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            if (" ".equals(field[i])) emptyCells.add(i);
        }
        int[] res = new int[emptyCells.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = emptyCells.get(i);
        }
        return res;
    }

    public boolean winning(String[] fieldArray, String player) {
        return (fieldArray[0].equals(player) && fieldArray[1].equals(player) && fieldArray[2].equals(player)) ||
                (fieldArray[3].equals(player) && fieldArray[4].equals(player) && fieldArray[5].equals(player)) ||
                (fieldArray[6].equals(player) && fieldArray[7].equals(player) && fieldArray[8].equals(player)) ||
                (fieldArray[0].equals(player) && fieldArray[3].equals(player) && fieldArray[6].equals(player)) ||
                (fieldArray[1].equals(player) && fieldArray[4].equals(player) && fieldArray[7].equals(player)) ||
                (fieldArray[2].equals(player) && fieldArray[5].equals(player) && fieldArray[8].equals(player)) ||
                (fieldArray[0].equals(player) && fieldArray[4].equals(player) && fieldArray[8].equals(player)) ||
                (fieldArray[2].equals(player) && fieldArray[4].equals(player) && fieldArray[6].equals(player));
    }

    public long minimax(String[] newField, String huPlayer, String aiPlayer, boolean isAiPlayer) {
        int[] availSpots = emptyIndices(newField);

        if (winning(newField, huPlayer)) return -10;
        else if (winning(newField, aiPlayer)) return 10;
        else if (availSpots.length == 0) return 0;

        long best;
        if (isAiPlayer) {
            best = -1000;

            for (int availSpot : availSpots) {
                newField[availSpot] = aiPlayer;
                best = Math.max(best, minimax(newField, huPlayer, aiPlayer, false));
                newField[availSpot] = " ";
            }
        } else {
            best = 1000;

            for (int availSpot : availSpots) {
                newField[availSpot] = huPlayer;
                best = Math.min(best, minimax(newField, huPlayer, aiPlayer, true));
                newField[availSpot] = " ";
            }
        }
        return best;
    }

    public MinimaxMove findBestMove(String[] newField, String huPlayer, String aiPlayer, boolean isAiPlayer) {
        long bestMove = -1000;
        long moveVal;
        MinimaxMove move = new MinimaxMove();

        int[] availSpots = emptyIndices(newField);

        for (int availSpot : availSpots) {
            if (isAiPlayer) {
                newField[availSpot] = aiPlayer;
                moveVal = minimax(newField, huPlayer, aiPlayer, false);
            } else {
                newField[availSpot] = huPlayer;
                moveVal = minimax(newField, huPlayer, aiPlayer, true);
            }
            newField[availSpot] = " ";

            if (moveVal > bestMove) {
                move.setIndex(availSpot);
                bestMove = moveVal;
            }
        }
        return move;
    }

}
