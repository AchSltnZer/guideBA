package com.example.bainfogame.komen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.bainfogame.MySingleton;
import com.example.bainfogame.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class reg_kmn extends AppCompatActivity {
    MaterialEditText email, password;
    SharedPreferences sharedPreferences;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_kmn);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        register = findViewById(R.id.regis_add);
        register.setOnClickListener(view -> {
            String tex_email = Objects
                    .requireNonNull(email.getText()).toString();
            String tex_password = Objects
                    .requireNonNull(password.getText()).toString();
            if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)) {
                Toast.makeText(reg_kmn.this,
                        "All Fields Required", Toast.LENGTH_SHORT).show();
            } else {
                regis(tex_email, tex_password);
            }
        });
    }
    private void regis(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(reg_kmn.this);
        progressDialog.setTitle("Registering your account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        String uRl = "https://zirorio.000webhostapp.com/regKmn.php";
        StringRequest request = new StringRequest(Request.Method.POST,
                uRl,
                (String response) -> {
                    if (response.equals("Register Berhasil")) {
                        Toast.makeText(reg_kmn.this,
                                response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(reg_kmn
                                .this, komenFeed.class));
                        progressDialog.dismiss();
                        finish();
                    } else {
                        Toast.makeText(reg_kmn.this,
                                response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, error -> {
            Toast.makeText(reg_kmn.this,
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

        MySingleton.getmInstance(reg_kmn.this).
                addToRequestQueue(request);
    }


}