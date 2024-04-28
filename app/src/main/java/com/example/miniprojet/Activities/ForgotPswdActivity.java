package com.example.miniprojet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniprojet.Metier.DBConnector;
import com.example.miniprojet.R;

public class ForgotPswdActivity extends AppCompatActivity {

    EditText userNameCheck;
    Button valide;
    DBConnector database;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pswd);
        userNameCheck = findViewById(R.id.username);
        valide = findViewById(R.id.valide);
        database = new DBConnector(this);
        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userNameCheck.getText().toString();
                Boolean checkUserName= database.checkUserName(username);
                if (checkUserName){
                     /*si le nom complet c-à-d username existe au niveau de la table clientInfo
                    la femme peut changer son mot de passe*/
                    Intent i = new Intent(getApplicationContext(), ResetPswdActivity.class);
                    i.putExtra("username",username);
                    startActivity(i);
                }
                else{
                    /*si y'a aucune femme enregistrée avec ce nom complet 'username'*/
                    Toast.makeText(ForgotPswdActivity.this, "username non existant ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}