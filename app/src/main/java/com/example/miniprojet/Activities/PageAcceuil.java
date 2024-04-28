package com.example.miniprojet.Activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.miniprojet.R;

public class PageAcceuil extends AppCompatActivity {
    ImageView femme, acheteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil);
        /*là on récupère mes éléments images femme HomeMade  et Client*/
        femme = findViewById(R.id.femme);
        acheteur = findViewById(R.id.client);
        /*la femme va être diriger vers l'interface d'inscription || connexion */
        femme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageAcceuil.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        /*le client ou bien acheteur de service va être dirigé directement vers la page des catégories*/
        acheteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageAcceuil.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}