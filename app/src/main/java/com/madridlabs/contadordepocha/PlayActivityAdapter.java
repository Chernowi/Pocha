package com.madridlabs.contadordepocha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayActivityAdapter extends RecyclerView.Adapter<PlayActivityAdapter.ViewHolder> {
    private ArrayList<Player> players;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onPlusFiveClick(int position);
        void onMinusFiveClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public PlayActivityAdapter(ArrayList<Player> playerList) {
        players = playerList;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView score;
        public Button plusFiveButton;
        public Button minusFiveButton;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            name = itemView.findViewById(R.id.player_name);
            score = itemView.findViewById(R.id.scoreText);

            plusFiveButton = itemView.findViewById(R.id.plusFive);
            minusFiveButton = itemView.findViewById(R.id.minusFive);

            plusFiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onPlusFiveClick(position);
                        }
                    }
                }
            });

            minusFiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onMinusFiveClick(position);
                        }
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_card, parent, false);
        ViewHolder vh = new ViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Player currentPlayer = players.get(position);
        holder.name.setText(currentPlayer.getName());
        holder.score.setText(String.valueOf(currentPlayer.getScore()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
