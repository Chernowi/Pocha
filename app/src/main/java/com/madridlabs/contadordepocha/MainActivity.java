package com.madridlabs.contadordepocha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Player> players;

    private Button addNameButton;
    private EditText nameBox;
    private ImageView deleteNameButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        players = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);

        addNameButton = findViewById(R.id.addNameButton);
        nameBox = findViewById(R.id.nameTextBox);

        buildRecyclerView();


        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                players.add(new Player(nameBox.getText().toString()));
                adapter.notifyItemInserted(players.size()-1);
            }
        });
    }

    public void removeItem(int position) {
        players.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void buildRecyclerView() {
        adapter = new Adapter(players);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }
}