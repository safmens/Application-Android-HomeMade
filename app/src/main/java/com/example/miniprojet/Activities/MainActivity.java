package com.example.miniprojet.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miniprojet.R;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH = 3500;
    Animation animation;
    private ImageView imageView;
    private TextView textView;


    /*là on va faire une animation à partir de la page de bienvenue actuelle vers la page d'acceuil*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        animation = AnimationUtils.loadAnimation(this,R.anim.animation);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        imageView.setAnimation(animation);
        textView.setAnimation(animation);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent= new Intent(MainActivity.this, PageAcceuil.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);
    }
}