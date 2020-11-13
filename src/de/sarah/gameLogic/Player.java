package de.sarah.gameLogic;

public class Player {

    protected String symbol;
    protected boolean isUser;

    public Player(String symbol, boolean isUser) {
        this.symbol = symbol;
        this.isUser = isUser;
    }

    public int[] makePlayerMove(Game game, Field field) {
        return new int[] {};
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }
}
