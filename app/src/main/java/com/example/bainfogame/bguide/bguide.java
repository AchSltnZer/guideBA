package com.example.bainfogame.bguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.bainfogame.R;

public class bguide extends AppCompatActivity {

    ImageView gd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bguide);
        gd1 = (ImageView) findViewById(R.id.imageView);
        gd1.setImageResource(R.drawable.gd1);
    }
}