package com.example.miniprojet.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.miniprojet.Metier.Client;
import com.example.miniprojet.Metier.DBHelper;
import com.example.miniprojet.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationActivity extends AppCompatActivity {

    EditText username, password, ConfirmPassword, phone, adress;
    Button registrationBtn;
    TextInputLayout textInputLayout;
    EditText Description;
    AutoCompleteTextView dropDownText;
    ImageView profileImage;
    DBHelper dbHelper;
    static int PICK_IMAGE_REQUEST = 99;
    Uri imagePath;
    Bitmap imageToStore;
    int insererimage;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //récupérer tous les champs d'inscription pour qu'on puisse récupérer les valeurs saisies
        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();
        profileImage = findViewById(R.id.profile);
        username = findViewById(R.id.usernamerow);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        adress = findViewById(R.id.adress);
        ConfirmPassword = findViewById(R.id.ConfirmPassword);
        registrationBtn = findViewById(R.id.registrationBtn);
        Description = findViewById(R.id.Description);
        textInputLayout = findViewById(R.id.services);
        dropDownText = findViewById(R.id.dropdown_text);


        //Liste de sélection de la catégorie
        String[] items = new String[]{
                "salés",
                "sucrés",
                "pates"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                RegistrationActivity.this,
                R.layout.list_item,
                items
        );
        dropDownText.setAdapter(adapter);
        //Handler du choix de l'image
        profileImage.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                choseImage();
                                            }
                                        });


        registrationBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( Description.getText().toString().isEmpty() || username.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || dropDownText.getText().toString().isEmpty() || password.getText().toString().isEmpty() || ConfirmPassword.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Un champ n'est pas rempli !", Toast.LENGTH_LONG).show();
                        } else if (!password.getText().toString().equals(ConfirmPassword.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Le mot de passe et celui de confirmation doivent se matcher !", Toast.LENGTH_LONG).show();
                        }else if(insererimage==0){
                            Toast.makeText(getApplicationContext(), "Selectionner une image !", Toast.LENGTH_LONG).show();
                        }


                        else {
                            //récuperer tous les valeurs saisies et faire l'inscription avec RegistrationCient de dbHelper
                            Client client = new Client(username.getText().toString(), password.getText().toString(), phone.getText().toString(), adress.getText().toString(), dropDownText.getText().toString(), ((BitmapDrawable)profileImage.getDrawable()).getBitmap(), Description.getText().toString());
                            if (dbHelper.RegistrationClient(client)) {

                                Toast.makeText(getApplicationContext(), "Compte enregistre avec succes !", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "Echec dans la creation du compte ! ", Toast.LENGTH_LONG).show();
                            }


                        }

                    }
                });
    }

    //permet de choisir une image à partir de la galorie
    private void choseImage() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        }
    }
    //passer de la page actuelle d'inscription vers celle de connexion
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(data==null || data.getData()==null){
                insererimage=0;
            }
            else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                profileImage.setImageBitmap(imageToStore);
                insererimage=1;
            }
        } catch (Exception e) {

        }
    }
}