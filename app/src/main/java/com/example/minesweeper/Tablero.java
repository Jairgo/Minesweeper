package com.example.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero {
    private List<Cell> cells;
    private int tam;
    private Random rand;
    private int pos;

    public Tablero(int tam){
        this.tam = tam;
        cells =  new ArrayList<>();
        for(int i = 0; i < tam * tam; i++)
            cells.add(new Cell(Cell.vacio));
    }

    public void setBombas(int cantBomb){
        rand = new Random();
        int i = 0;
        while(i < cantBomb){
            pos = rand.nextInt((this.tam * this.tam) - 1 ) + 1;
            if(cells.get(pos).getVal() == Cell.vacio){
                cells.get(pos).setVal(Cell.bomba);
                i++;
            }
        }
    }

    /**
     * @param pos Es la posición de la celda quefue clickeada
     * @param tam Es el tamaño del tablero, para poder calcular el tamaño total de celdas
     * @return la cantidad de bombas que tiene al rededor esa celda
    */
    public int leftClickCell(int pos, int tam){

        if(!cells.get(pos).isRevelado())
            if(cells.get(pos).getVal() == Cell.bomba)
                return Cell.bomba;
        else return -2;

        int countBomb = 0;
        int real = tam * tam;

        if(pos == 0){ // Checo si es la esquina superior izquierda
            if(cells.get(pos + 1).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + tam).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + (tam + 1)).getVal() == Cell.bomba)
                countBomb++;

        }
        else if(pos == 0 + tam - 1){ // Checo si es la esquina superior deerecha
            if(cells.get(pos - 1).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + (tam - 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + tam).getVal() == Cell.bomba)
                countBomb++;
        }
        else if(pos == real - tam){ // Checo si es la esquina inferior izquierda
            if(cells.get(pos - tam ).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - (tam + 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + 1).getVal() == Cell.bomba)
                countBomb++;
        }
        else if(pos == real - 1){ // Checo si es la esquina inferior derecha
            if(cells.get(pos - 1).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - (tam - 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - tam ).getVal() == Cell.bomba)
                countBomb++;
        }
        else if(pos % tam == 0){ // Checo si es una celda lateral izquierda
            if(cells.get(pos - tam ).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - (tam + 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + 1).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + tam).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + (tam + 1)).getVal() == Cell.bomba)
                countBomb++;
        }
        else if((pos + 1) % tam == 0){ // Checo si es una celda lateral derecha
            if(cells.get(pos + tam).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + (tam - 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - 1).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - tam ).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - (tam - 1)).getVal() == Cell.bomba)
                countBomb++;
        }
        else {
            if(cells.get(pos + tam).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + (tam + 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + (tam - 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos + 1).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - 1).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - tam ).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - (tam - 1)).getVal() == Cell.bomba)
                countBomb++;
            if(cells.get(pos - (tam + 1)).getVal() == Cell.bomba)
                countBomb++;
        }

        cells.get(pos).setRevelado(true);
        return countBomb;
    }

    /**
     * @param pos es la posicion de donde se marcó la casilla
     * @return true si la celda si tenía una mina, false si la celda no tiene mina o ya fue flagged
    */
    public boolean rightClickCell(int pos){
        if(!cells.get(pos).isFlagged()){
            cells.get(pos).setFlagged(true);
            if(cells.get(pos).getVal() == -1)
                return true;
        }
        return false;
    }

    public List<Cell> getCells() {
        return this.cells;
    }
}
