package com.example.minesweeper;

public class Cell {
    public static final int bomba = -1;
    public static final int vacio = 0;

    private int val;
    private boolean revelado;
    private boolean flagged;

    public Cell(int val){
        this.val = val;
        this.revelado = false;
        this.flagged = false;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    public boolean isRevelado(){
        return revelado;
    }

    public void setRevelado(boolean revelado) {
        this.revelado = revelado;
    }

    public boolean isFlagged(){
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}
