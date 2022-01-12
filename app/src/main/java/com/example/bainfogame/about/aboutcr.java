package com.example.bainfogame.about;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.bainfogame.R;


public class aboutcr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutcr);
        ImageView img1 = (ImageView) findViewById(R.id.abot1);
        img1.setImageResource(R.drawable.aboutim1);
        ImageView img2 = (ImageView) findViewById(R.id.abot2);
        img2.setImageResource(R.drawable.aboutim2);
    }
}