package com.example.bainfogame.equipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bainfogame.MySingleton;
import com.example.bainfogame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

public class EqActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL_ST = "https://zirorio.000webhostapp.com/getEq.php";
    private static final String URL_S = "http://baguideppb.42web.io/getEq.php";
    private RecyclerView recyclerView;
    private eqAdapter adapter;
    private ArrayList<eqmodel> eq_list;
    Button bh, bg, bs, bh2, bb, bb2, bw, bc, bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eq);
        bh = (Button) findViewById(R.id.but_hat);
        bg = (Button) findViewById(R.id.but_glove);
        bs = (Button) findViewById(R.id.but_sho);
        bh2 = (Button) findViewById(R.id.but_hpin);
        bb = (Button) findViewById(R.id.but_badge);
        bb2 = (Button) findViewById(R.id.but_bag);
        bw = (Button) findViewById(R.id.but_watch);
        bc = (Button) findViewById(R.id.but_charm);
        bn = (Button) findViewById(R.id.but_necklace);

        bh.setOnClickListener(this);
        bg.setOnClickListener(this);
        bs.setOnClickListener(this);
        bh2.setOnClickListener(this);
        bb.setOnClickListener(this);
        bb2.setOnClickListener(this);
        bw.setOnClickListener(this);
        bc.setOnClickListener(this);
        bn.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        eq_list = new ArrayList<>();
        setRecycleview();
        getEq();


    }

    private void setRecycleview() {
        adapter = new eqAdapter(this, eq_list);
        recyclerView.setAdapter(adapter);
    }


    void getEq() {
        CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL ) );
        StringRequest request = new StringRequest(Request.Method.GET, URL_ST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.d("volley", "response : " + response.toString());
                    JSONArray array = new JSONArray(response);
                    Log.d("volley", "response : " + response.toString());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject users = array.getJSONObject(i);
                        Log.d("volley", "test : " + users.getString("tier"));
                        eq_list.add(new eqmodel(
                                users.getString("tier"),
                                users.getString("nmeq"),
                                users.getString("mapdr"),
                                users.getString("type"),
                                users.getString("img"))
                        );
                    }
                    adapter = new eqAdapter(EqActivity.this, eq_list);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MySingleton.getmInstance(EqActivity.this).addToRequestQueue(request);
    }

    void filter(String text) {
        ArrayList<eqmodel> temp = new ArrayList();
        for (eqmodel d : eq_list) { //or use .equal(text) with you want equal match //use .toLowerCase() for better matches
            if(d.getType().contains(text)){ temp.add(d); }
        }
        Log.d("tenm", "response : " + temp.toString());
        adapter.updatelist(temp);
    }
    @Override
    public void onClick(View view) {
        TextView tipe = (TextView) findViewById(R.id.table_about);
        switch (view.getId()) {
            case R.id.but_hat:
                tipe.setText("Hat");
                filter(tipe.getText().toString());
                break;
            case R.id.but_glove:
                tipe.setText("Gloves");
                filter(tipe.getText().toString());
                break;
            case R.id.but_sho:
                tipe.setText("Shoe");
                filter(tipe.getText().toString());
                break;
            case R.id.but_hpin:
                tipe.setText("Hairpin");
                filter(tipe.getText().toString());
                break;
            case R.id.but_badge:
                tipe.setText("Badge");
                filter(tipe.getText().toString());
                break;
            case R.id.but_bag:
                tipe.setText("Bag");
                filter(tipe.getText().toString());
                break;
            case R.id.but_watch:
                tipe.setText("Watch");
                filter(tipe.getText().toString());
                break;
            case R.id.but_charm:
                tipe.setText("Charm");
                filter(tipe.getText().toString());
                break;
            case R.id.but_necklace:
                tipe.setText("Necklace");
                filter(tipe.getText().toString());
                break;

        }
    }
}
