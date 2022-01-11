package com.example.bainfogame.equipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bainfogame.R;

import java.util.ArrayList;
import java.util.List;

public class EqActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL_ST = "https://zirorio.000webhostapp.com/getEq.php";
    private RecyclerView recyclerView;
    private eqAdapter adapter;
    Button bh,bg,bs,bh2,bb,bb2,bw,bc,bn;

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
        setRecycleview();

    }

    private void setRecycleview() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new eqAdapter(this,getList());
        recyclerView.setAdapter(adapter);
    }

    private List<eqmodel> getList(){
        List<eqmodel> eq_list = new ArrayList<>();
        eq_list.add(new eqmodel("1","H","1"));
        eq_list.add(new eqmodel("2","B","1"));
        eq_list.add(new eqmodel("3","C","1"));
        return  eq_list;
    }

    @Override
    public void onClick(View view) {
        TextView tipe = (TextView) findViewById(R.id.table_about);
        switch(view.getId()) {
            case R.id.but_hat:
                tipe.setText("Hat");
                break;
            case R.id.but_glove:
                tipe.setText("Gloves");
                break;
            case R.id.but_sho:
                tipe.setText("Shoe");
                break;
            case R.id.but_hpin:
                tipe.setText("Hairpin");
                break;
            case R.id.but_badge:
                tipe.setText("Badge");
                break;
            case R.id.but_bag:

                tipe.setText("Bag");
                break;
            case R.id.but_watch:
                tipe.setText("Watch");
                break;
            case R.id.but_charm:
                tipe.setText("Charm");
                break;
            case R.id.but_necklace:
                tipe.setText("Necklace");
                break;

    }
    }
}