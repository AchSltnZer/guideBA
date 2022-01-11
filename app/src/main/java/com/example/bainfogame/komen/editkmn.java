package com.example.bainfogame.komen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.bainfogame.MySingleton;
import com.example.bainfogame.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

public class editkmn extends AppCompatActivity {
    Button upd;
    TextView edit_nm;
    MaterialEditText komen;
    SharedPreferences sharedPreferences, comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editkmn);
        sharedPreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);
        comment = getSharedPreferences("Comment",
                Context.MODE_PRIVATE);
        String cm_id = comment.getString("cm_id",null);
        String comments = comment.getString("komen",null);
        edit_nm = findViewById(R.id.edit_usnm);
        edit_nm.setText(sharedPreferences.getString("username",null));
        komen = findViewById(R.id.updatekmn);
        komen.setText(comments);
        upd = findViewById(R.id.upd_btn);
        upd.setOnClickListener(view -> {
            update(cm_id,komen.getText().toString());
        });


    }
    void update(final String cm_id,final String komen){
        String uRl = "https://zirorio.000webhostapp.com/updkmn.php";
        StringRequest request = new StringRequest(Request.Method.POST,
                uRl,
                (String response) -> {
                    if (response.equals("Berhasil")) {
                        Toast.makeText(editkmn.this,
                                response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(editkmn
                                .this, komenFeed.class));
                        finish();
                    } else {
                        Toast.makeText(editkmn.this,
                                response, Toast.LENGTH_SHORT).show();
                    }
                }, error -> {
            Toast.makeText(editkmn.this,
                    error.toString(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("csid", cm_id);
                param.put("komen", komen);
                return param;
            }
        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(30000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getmInstance(editkmn.this).
                addToRequestQueue(request);}
}