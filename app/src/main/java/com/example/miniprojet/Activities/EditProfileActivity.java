package com.example.miniprojet.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniprojet.Metier.Client;
import com.example.miniprojet.Metier.DBConnector;
import com.example.miniprojet.R;


public class EditProfileActivity extends AppCompatActivity {

    Client client;


    EditText username,phone, adress;
    Button updatBtn,cancelBtn;
    EditText Description;
    AutoCompleteTextView dropDownText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        DBConnector dbConnector = new DBConnector(getApplicationContext());
        client= new Client();

        username = findViewById(R.id.usernamerow);
        phone = findViewById(R.id.phone);
        adress = findViewById(R.id.adress);
        Description = findViewById(R.id.Description);
        dropDownText = findViewById(R.id.dropdown_text);
        Intent i= getIntent();
        client = (Client) i.getSerializableExtra("client");
        loadData();

        updatBtn = findViewById(R.id.updatBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        //handler du bouton update
        updatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //modifier dans la table clientInfos les nouvelles infos entrées par la femme
                dbConnector.update(username.getText().toString()
                        , phone.getText().toString()
                        , adress.getText().toString()
                        , dropDownText.getText().toString()
                        , Description.getText().toString()
                );

                //Afficher les nouvelles valeurs saisies par la femme dans les champs correspondants
                phone.setText(phone.getText().toString());
                adress.setText(adress.getText().toString());
                dropDownText.setText(dropDownText.getText().toString());
                Description.setText(Description.getText().toString());
                Toast.makeText(EditProfileActivity.this, "Profile est modifier avec succès", Toast.LENGTH_LONG).show();
                Intent intent= new Intent(EditProfileActivity.this, ProfileActivity.class);
                intent.putExtra("client",client);
                startActivity(intent);

            }

        });

        //handler du bouton cancel
        //annuler les modifications et revenir aux informations originales de la femme
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phone.setText(client.getPhone());
                adress.setText(client.getAdress());
                dropDownText.setText(client.getService());
                Description.setText(client.getDescription());

            }
        });



    }

    private  void loadData(){


        Intent intent =getIntent();
        client = (Client) intent.getSerializableExtra("client");
        username.setText(client.getUserName());
        phone.setText(client.getPhone());
        adress.setText(client.getAdress());
        Description.setText(client.getDescription());
        dropDownText.setText(client.getService());

    }
}