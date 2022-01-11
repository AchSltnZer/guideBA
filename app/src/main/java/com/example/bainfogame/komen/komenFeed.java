package com.example.bainfogame.komen;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bainfogame.MySingleton;
import com.example.bainfogame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class komenFeed extends AppCompatActivity implements kmnAdapter.OnNotListener{

    private static final String url_k = "https://zirorio.000webhostapp.com/getkmn.php";
    private RecyclerView recyclerView;
    private kmnAdapter adapter;
    private ArrayList<kmn> kmnlists;
    Button logreg,logout;
    SharedPreferences sharedPreferences, comment;
    TextView nm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komen_feed);
        Button addk = (Button) findViewById(R.id.kmnbtn);
        sharedPreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);
        comment = getSharedPreferences("Comment",
                Context.MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kmnlists = new ArrayList<>();
        addData();
        getKmn();
        adapter = new kmnAdapter(kmnlists, this);

        addk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(komenFeed.this, addkmn.class));
            }
        });
        logreg = (Button) findViewById(R.id.button);
        logreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(komenFeed.this, log_kmn.class));
            }
        });
        logout = (Button) findViewById(R.id.logout);
        String loginStatus = sharedPreferences
                .getString("loggedin","");
        if (loginStatus.equals("loggedin")) {
            addk.setVisibility(View.VISIBLE);
            logreg.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }else {
            logout.setVisibility(View.GONE);
            addk.setVisibility(View.GONE);
        }
        nm = (TextView) findViewById(R.id.shw_nm);
        nm.setText("Logged User : " +sharedPreferences.getString("username",null));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("loggedin", "logout");
                editor.putString("username", "_____");;
                editor.putString("id", "_____");
                editor.apply();
                finish();
                startActivity(getIntent());

            }
        });
    }


    void addData(){
        kmnlists.add(new kmn("0","dummy","testing"));

    }

    void getKmn(){
        StringRequest request = new StringRequest(Request.Method.GET,
                url_k, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    Log.d("volley", "response : " + response.toString());
                    for (int i = 0; i < array.length(); i++){
                        JSONObject users = array.getJSONObject(i);
                        kmnlists.add(new kmn(
                                users.getString("cm_id"),
                                users.getString("username"),
                                users.getString("komen")
                        ));
                    }
                    recyclerView.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        MySingleton.getmInstance(komenFeed.this).
                addToRequestQueue(request);
    }

    @Override
    public void onClick(int positon) {
        Log.d(TAG, "onClick() returned: " + kmnlists.get(positon).getNama() +" "+ sharedPreferences.getString("username",null));
        if(sharedPreferences.getString("username", null).equals(kmnlists.get(positon).getNama())){
            final CharSequence[] dialogitem = {"Edit", "Hapus"};
            AlertDialog.Builder builder = new AlertDialog.Builder(komenFeed.this);
            builder.setTitle("Pilihan");
            builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch(item){
                        case 0 :
                            SharedPreferences.Editor editor = comment.edit();
                            editor.putString("cm_id", kmnlists.get(positon).getCm_id());
                            editor.putString("komen", kmnlists.get(positon).getKomen());
                            editor.apply();
                            Log.d(TAG, "onClick() returned: " + comment.getString("cm_id", null));
                            startActivity(new Intent(komenFeed.this,editkmn.class));
                            break;
                        case 1 :
                            AlertDialog alertDialog = new AlertDialog.Builder(komenFeed.this).create();
                            alertDialog.setTitle("Delete");
                            alertDialog.setMessage("Lanjutkan Hapus ?");
                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String uRl = "https://zirorio.000webhostapp.com/deletekmn.php";
                                    StringRequest request = new StringRequest(Request.Method.POST,
                                            uRl,
                                            (String response) -> {
                                                if (response.equals("Berhasil")) {
                                                    Toast.makeText(komenFeed.this,
                                                            response, Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    startActivity(getIntent());
                                                } else {
                                                    Toast.makeText(komenFeed.this,
                                                            response, Toast.LENGTH_SHORT).show();
                                                }
                                            }, error -> {
                                        Toast.makeText(komenFeed.this,
                                                error.toString(), Toast.LENGTH_SHORT).show();
                                    }) {
                                        @Override
                                        protected Map<String, String> getParams() {
                                            HashMap<String, String> param = new HashMap<>();
                                            param.put("id", kmnlists.get(positon).getCm_id());
                                            return param;
                                        }
                                    };
                                    request.setRetryPolicy(
                                            new DefaultRetryPolicy(30000,
                                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                                    MySingleton.getmInstance(komenFeed.this).
                                            addToRequestQueue(request);

                                }
                            });
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                            alertDialog.show();
                            break;
                    }
                }
            });
            builder.create().show();
        }else {

        }

    }
}