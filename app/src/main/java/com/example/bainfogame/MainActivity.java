package com.example.bainfogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.bainfogame.Adapter.ItemAdapter;
import com.example.bainfogame.Adapter.Items;
import com.example.bainfogame.about.aboutcr;
import com.example.bainfogame.bguide.bguide;
import com.example.bainfogame.equipment.EqActivity;
import com.example.bainfogame.komen.komenFeed;
import com.example.bainfogame.student.StudentList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnNoteListener {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<Items> userArrayList;
    private int as[]= {R.drawable.student, R.drawable.equipment, R.drawable.bg_tips, R.drawable.cmn_ic, R.drawable.camp, R.drawable.about};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new ItemAdapter(userArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    void addData() {
        userArrayList = new ArrayList<>();
        userArrayList.add(new Items("Students",as[0]));
        userArrayList.add(new Items("Equipments",as[1]));;
//        userArrayList.add(new Items("Map Info",as[4]));
        userArrayList.add(new Items("Tips Pemula",as[2]));
        userArrayList.add(new Items("Comment",as[3]));
        userArrayList.add(new Items("About",as[5]));
    }
    @Override
    public void onClick(int positon) {
        if(userArrayList.get(positon).getNama().equals("Students")){
        startActivity(new Intent(MainActivity.this, StudentList.class));
        }else if(userArrayList.get(positon).getNama().equals("Equipments")){
            startActivity(new Intent(MainActivity.this, EqActivity.class));
        }else if(userArrayList.get(positon).getNama().equals("Tips Pemula")){
            startActivity(new Intent(MainActivity.this, bguide.class));
        }else if(userArrayList.get(positon).getNama().equals("Campaign")){
        startActivity(new Intent(MainActivity.this, komenFeed.class));
        }else if(userArrayList.get(positon).getNama().equals("Comment")){
            startActivity(new Intent(MainActivity.this, komenFeed.class));
        }else if(userArrayList.get(positon).getNama().equals("About")){
            startActivity(new Intent(MainActivity.this, aboutcr.class));
        }
    }

}