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

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView  recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        List<Client> femmesList = new ArrayList<>();
        DBConnector myDB = new DBConnector(RecyclerViewActivity.this);

        femmesList=myDB.readAllData();
        setClientItemRecycler(femmesList);



    }


    //permet de passer la liste des femmes qui seront dans la recyclerView
    private void setClientItemRecycler(List<Client> femmesList) {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecyclerViewActivity.this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(RecyclerViewActivity.this,femmesList);
        recyclerView.setAdapter(customAdapter);

    }


}