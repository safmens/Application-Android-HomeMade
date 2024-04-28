package com.example.miniprojet.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojet.Metier.Client;
import com.example.miniprojet.Metier.DBConnector;
import com.example.miniprojet.R;

import java.util.ArrayList;
import java.util.List;

public class SugarRecyclerActivity extends AppCompatActivity {
    RecyclerView  recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        List<Client> femmesList = new ArrayList<>();
        DBConnector myDB = new DBConnector(SugarRecyclerActivity.this);

        femmesList=myDB.readAllSUGARData();
        setClientItemRecycler(femmesList);
        
    }


    private void setClientItemRecycler(List<Client> femmesList) {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SugarRecyclerActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(SugarRecyclerActivity.this,femmesList);
        recyclerView.setAdapter(customAdapter);

    }


}