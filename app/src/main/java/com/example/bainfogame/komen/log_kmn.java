package com.example.bainfogame.komen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.bainfogame.MySingleton;
import com.example.bainfogame.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class log_kmn extends AppCompatActivity {

    MaterialEditText email, password;
    SharedPreferences sharedPreferences;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_kmn);
        email = findViewById(R.id.regis_email);
        password = findViewById(R.id.regis_password);
        sharedPreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);
        sharedPreferences.contains("id");
        sharedPreferences.contains("username");
        String loginStatus = sharedPreferences
                .getString("loggedin","");
        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            String tex_email = Objects
                    .requireNonNull(email.getText()).toString();
            String tex_password = Objects
                    .requireNonNull(password.getText()).toString();
            if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)) {
                Toast.makeText(log_kmn.this,
                        "All Fields Required", Toast.LENGTH_SHORT).show();
            } else {
                login(tex_email, tex_password);
            }
        });
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(log_kmn.this, reg_kmn.class));
            }
        });

    }

    private void login(final String email, final String password) {
        final ProgressDialog progressDialog = new ProgressDialog(
                log_kmn.this);
        progressDialog.setTitle("Logging your account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        String uRl = "https://zirorio.000webhostapp.com/logkmn.php";
        StringRequest request = new StringRequest(Request.Method.POST,
                uRl,
                (String response) -> {
                    try {
                        JSONObject jObj = new JSONObject(response);
                        String message = jObj.getString("message");
                        String username = jObj.getString("username");
                        String id = jObj.getString("id");
                        if (message.equals("Login Success")) {
                            Toast.makeText(log_kmn.this,
                                    message, Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", username);
                            editor.putString("id", id);
                                editor.putString("loggedin", "loggedin");
                            editor.apply();
                            startActivity(new Intent(log_kmn
                                    .this, komenFeed.class));
                            progressDialog.dismiss();
                            finish();
                        } else {
                            Toast.makeText(log_kmn.this,
                                    message, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }, error -> {
            Toast.makeText(log_kmn.this,
                    error.toString(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("psw", password);
                return param;
            }
        };
        request.setRetryPolicy(
                new DefaultRetryPolicy(30000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        MySingleton.getmInstance(log_kmn.this).
                addToRequestQueue(request);
    }
}