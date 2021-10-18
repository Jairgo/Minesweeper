package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{
    Tablero tablero;
    int tam, cantBomb;
    int markedMinesTrue = 0, markedMinesFalse = 0;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linLay1 = findViewById(R.id.linearLay1);
        LinearLayout linLay2 = findViewById(R.id.linearLay2);
        LinearLayout linLay3 = findViewById(R.id.linearLay3);
        LinearLayout linLay4 = findViewById(R.id.linearLay4);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        for (int i = 0; i < 10; i++) {
            Button newButton = new Button(this);
            newButton.setText("" + i);
            newButton.setId(i);
            //newButton.setOnClickListener(onClickListener);

            linLay1.addView(newButton, layoutParams);
        }
        for (int i = 0; i < 10; i++) {
            Button newButton = new Button(this);
            newButton.setText("" + i);
            newButton.setId(i);
            //newButton.setOnClickListener(onClickListener);

            linLay2.addView(newButton, layoutParams);
        }
        for (int i = 0; i < 10; i++) {
            Button newButton = new Button(this);
            newButton.setText("" + i);
            newButton.setId(i);
            //newButton.setOnClickListener(onClickListener);

            linLay3.addView(newButton, layoutParams);
        }
        for (int i = 0; i < 10; i++) {
            Button newButton = new Button(this);
            newButton.setText("" + i);
            newButton.setId(i);
            //newButton.setOnClickListener(onClickListener);

            linLay4.addView(newButton, layoutParams);
        }


        tam = 9;
        cantBomb = 10;

        newGame(tam, cantBomb);

        if(tablero.leftClickCell(8, tam) == -1){
            Toast.makeText(getApplicationContext(), "Has explotado una mina. ¡Suerte para la otra!", Toast.LENGTH_LONG).show();
            gameOver = true;
        }else{
            // TODO Mostrar la cantidad de minas al rededor de la celda
        }

        if(tablero.rightClickCell(9)){
            markedMinesTrue++;
            if(markedMinesTrue == cantBomb){
                Toast.makeText(getApplicationContext(), "Has encontrado todas las minas, ¡Felicidades, Ganaste la partida!", Toast.LENGTH_LONG).show();
                gameOver = true;
            }
        }else{
            markedMinesFalse++;
            if(markedMinesFalse == cantBomb){
                Toast.makeText(getApplicationContext(), "Te faltaron minas por encontrar, has perdio la partida, ¡Suerte para la otra! ", Toast.LENGTH_LONG).show();
                gameOver = true;
            }
        }






    }

    private void newGame(int tam, int cantBomb){
        tablero = new Tablero(tam); // Se crea el tablero del tamaño deseado
        tablero.setBombas(cantBomb); // Se colocan las bombas en el tablero de manera random
        this.gameOver = false;
        this.markedMinesTrue = 0;
        this.markedMinesFalse = 0;
    }

}