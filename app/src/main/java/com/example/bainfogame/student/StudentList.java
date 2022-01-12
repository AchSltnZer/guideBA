package com.example.bainfogame.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bainfogame.MainActivity;
import com.example.bainfogame.MySingleton;
import com.example.bainfogame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentList extends AppCompatActivity implements AdapterSt.OnNoteListener {

    private static final String URL_ST = "https://zirorio.000webhostapp.com/getStudent.php";
    private RecyclerView recyclerView;
    private AdapterSt adapter;
    private ArrayList<Student> userArrayList;
    Button all, strik, spes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        all = (Button) findViewById(R.id.showall);
        strik = (Button) findViewById(R.id.showst);
        spes = (Button) findViewById(R.id.showsp);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userArrayList = new ArrayList<>();
        getStudent();
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter("all");
            }
        });
        strik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter("Striker");
            }
        });
        spes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter("Special");
            }
        });

    }

    void getStudent() {
        StringRequest request = new StringRequest(Request.Method.GET,
                URL_ST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject users = array.getJSONObject(i);

                            userArrayList.add(new Student(
                                    users.getString("name"),
                                    users.getString("type"),
                                    users.getString("school"),
                                    users.getString("atype"),
                                    users.getString("dtype"),
                                    users.getString("imgs"),
                                    users.getInt("rating")));

                    }
                    adapter = new AdapterSt(StudentList.this, userArrayList, StudentList.this);

                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getmInstance(StudentList.this).
                addToRequestQueue(request);
    }


    void filter(String text){
            ArrayList<Student> temp = new ArrayList();
            for(Student d: userArrayList){
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches
                if(d.getType().contains(text)){
                    temp.add(d);
                }else if(text.equals("all")){
                    temp.add(d);
                }
            }
            //update recyclerview
            adapter.updateList(temp);
        }
    @Override
    public void onClick(int positon) {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}