package com.example.minesweeper;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minesweeper.Cell;
import com.example.minesweeper.R;

import java.util.List;

public class TableroRecyclerAdapter extends RecyclerView.Adapter<TableroRecyclerAdapter.MineTileViewHolder> {
    private List<Cell> cells;
    private OnCellClickListener lis;

    public TableroRecyclerAdapter(List<Cell> cells, OnCellClickListener lis) {
        this.cells = cells;
        this.lis = lis;
    }

    @NonNull
    @Override
    public MineTileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new MineTileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MineTileViewHolder holder, int position) {
        holder.bind(cells.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    class MineTileViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;

        public MineTileViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.item_cell_value);
        }

        public void bind(final Cell cell) {

            itemView.setBackgroundColor(Color.rgb(233,242,241));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    int ret = lis.cellClick(pos);

                    if (ret == Cell.bomba){
                        valueTextView.setText(R.string.bomb);
                        itemView.setBackgroundColor(Color.rgb(200,74,74));
                    }

                    else {
                        valueTextView.setText(Integer.toString(ret));
                        itemView.setBackgroundColor(Color.rgb(230,173,99));
                    }

                    itemView.setEnabled(false);

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = getAdapterPosition();
                    lis.cellClikLong(pos);

                    valueTextView.setText(R.string.flag);
                    return true;
                }
            });

        }

    }
}