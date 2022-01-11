package com.example.bainfogame.komen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class addkmn extends AppCompatActivity {

    TextView uname;
    MaterialEditText komn;
    Button add;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addkmn);
        sharedPreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);
        uname =(TextView)findViewById(R.id.add_usnm);
        uname.setText(sharedPreferences.getString("id",null));
        komn = findViewById(R.id.editkmn);
        add = (Button) findViewById(R.id.add_btn);
        add.setOnClickListener(v -> {
            adddata(uname.getText().toString(),sharedPreferences.getString("username", null),komn.getText().toString());
        });
    }

private void adddata(final String us_id, final String username, final String komen){
    final ProgressDialog progressDialog = new ProgressDialog(addkmn.this);
    progressDialog.setTitle("Adding your comment");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    progressDialog.setIndeterminate(false);
    progressDialog.show();
    String uRl = "https://zirorio.000webhostapp.com/addKmn.php";
    StringRequest request = new StringRequest(Request.Method.POST,
            uRl,
            (String response) -> {
                if (response.equals("Berhasil")) {
                    Toast.makeText(addkmn.this,
                            response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(addkmn
                            .this, komenFeed.class));
                    progressDialog.dismiss();
                    finish();
                } else {
                    Toast.makeText(addkmn.this,
                            response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }, error -> {
        Toast.makeText(addkmn.this,
                error.toString(), Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }) {
        @Override
        protected Map<String, String> getParams() {
            HashMap<String, String> param = new HashMap<>();
            param.put("usname", username);
            param.put("usid", us_id);
            param.put("komen", komen);
            return param;
        }
    };
    request.setRetryPolicy(
            new DefaultRetryPolicy(30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    MySingleton.getmInstance(addkmn.this).
            addToRequestQueue(request);
}
}