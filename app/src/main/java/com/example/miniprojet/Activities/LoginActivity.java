package com.example.miniprojet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniprojet.Metier.Client;
import com.example.miniprojet.Metier.DBHelper;
import com.example.miniprojet.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginBtn;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instance de dbHelper pour utiliser la méthode de connexion
        dbHelper= new DBHelper(this);
        dbHelper.OpenDB();

        username = findViewById(R.id.usernamerow);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);


        //handler du bouton de connexion
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si la femme existe déjà y'aura une femme dans la liste et userDetails.size() !=0
                ArrayList<Client> userDetails = dbHelper.LoginClient(username.getText().toString(),password.getText().toString());
                if(userDetails.size() !=0){
                    Client client = userDetails.get(0);
                    Toast.makeText(getApplicationContext(),"valide user",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(LoginActivity.this, CategoryActivitydeux.class);
                    intent.putExtra("client",client);
                    startActivity(intent);

                }
                else{
                    //y'a aucune femme dans la table clientInfo avec le mot de passe et le username passé en param
                    Toast.makeText(getApplicationContext(),"invalide user",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
    //redirection vers la page d'incription
    public void Register(View view){
        Intent intent= new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);

    }
    //redirection vers la page de changement du mot de passe si oublié
    public void motdpOublie(View view){
        Intent intent= new Intent(LoginActivity.this, ForgotPswdActivity.class);
        startActivity(intent);

    }

}