package com.madridlabs.contadordepocha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainActivityAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Player> players;

    private Button addNameButton;
    private EditText nameBox;

    private FloatingActionButton playButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        players = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        addNameButton = findViewById(R.id.addNameButton);
        nameBox = findViewById(R.id.nameTextBox);

        playButton = findViewById(R.id.play);

        buildRecyclerView();


        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                players.add(new Player(nameBox.getText().toString()));
                adapter.notifyItemInserted(players.size()-1);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });
    }

    public void removeItem(int position) {
        players.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("players", players);
        startActivity(intent);
    }

    public void buildRecyclerView() {
        adapter = new MainActivityAdapter(players);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainActivityAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }
}