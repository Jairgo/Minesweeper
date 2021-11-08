package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnCellClickListener{
    Tablero tablero;
    int tam, cantBomb;
    int markedMines = 0;
    private boolean findAll = true;
    public boolean gameOver = false;
    RecyclerView gridRecyclerView;
    TableroRecyclerAdapter tableroRecyclerAdapter;
    TextView minesLeft, emoji;
    private Button btnStart;
    Chronometer cronometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tam = 9;
        cantBomb = 10;

        btnStart = findViewById(R.id.btnStart);
        cronometro = (Chronometer) findViewById(R.id.cronometro);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                minesLeft = findViewById(R.id.minesLeft);
                emoji = findViewById(R.id.emoji);

                minesLeft.setText(Integer.toString(cantBomb));
                btnStart.setEnabled(false);
                newGame(tam, cantBomb);
            }
        });

    }

    private void newGame(int tam, int cantBomb){
        btnStart.setText(R.string.iniciar);
        emoji.setText(R.string.Game);

        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();



        tablero = new Tablero(tam); // Se crea el tablero del tamaño deseado
        tablero.setBombas(cantBomb); // Se colocan las bombas en el tablero de manera random
        this.gameOver = false;
        this.markedMines = 0;

        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 9));

        tableroRecyclerAdapter = new TableroRecyclerAdapter(tablero.getCells(), this);
        gridRecyclerView.setAdapter(tableroRecyclerAdapter);

    }

    private void endGame(){
        btnStart.setEnabled(true);
        btnStart.setText("Restart");
        gameOver = true;
        emoji.setText(R.string.GameOver);
        cronometro.stop();


        int count = tableroRecyclerAdapter.getItemCount();
        for(int i = 0; i < count; i++)
            gridRecyclerView.getChildAt(i).setEnabled(false);
        // tablero.revealBombs();
    }

    @Override
    public int cellClick(int cellPos) {
        int ret = tablero.leftClickCell(cellPos, tam);
        if(ret == Cell.bomba){
            Toast.makeText(getApplicationContext(), "Has explotado una mina. ¡Suerte para la otra!", Toast.LENGTH_LONG).show();
            endGame();
        }

        return ret;
    }

    @Override
    public void cellClikLong(int cellPos) {

        markedMines++;
        if(tablero.rightClickCell(cellPos)){
            if(markedMines == cantBomb && findAll){
                Toast.makeText(getApplicationContext(), "Has encontrado todas las minas, ¡Felicidades, Ganaste la partida!", Toast.LENGTH_LONG).show();
                gameOver = true;
                endGame();
            }
        }else
            findAll = false;

        if(markedMines == cantBomb && !findAll){
            Toast.makeText(getApplicationContext(), "Te faltaron minas por encontrar, has perdido la partida, ¡Suerte para la otra! ", Toast.LENGTH_LONG).show();
            gameOver = true;
            endGame();
        }

        int mineleft = cantBomb - markedMines;
        if(mineleft >= 0)
            minesLeft.setText(Integer.toString(mineleft));

    }

}