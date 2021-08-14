package com.madridlabs.contadordepocha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {
    private ArrayList<Player> players;
    private PlayActivityAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getSupportActionBar().hide();

        if(getIntent().getExtras() != null) {
            players = (ArrayList<Player>) getIntent().getSerializableExtra("players");
        }

        recyclerView = findViewById(R.id.recyclerViewPlay);
        layoutManager = new LinearLayoutManager(this);

        buildRecyclerView();
    }

    public void buildRecyclerView() {
        adapter = new PlayActivityAdapter(players);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PlayActivityAdapter.OnItemClickListener() {
            @Override
            public void onPlusFiveClick(int position) {
                players.get(position).increaseFive();
                buildRecyclerView();
            }

            @Override
            public void onMinusFiveClick(int position) {
                players.get(position).decreaseFive();
                buildRecyclerView();
            }
        });
    }
}