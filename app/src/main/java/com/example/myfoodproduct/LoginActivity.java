package com.example.myfoodproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ArrayList<String> array = new ArrayList<>();
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText edEmail, edPassword;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.ed_email);
        edPassword = findViewById(R.id.ed_password);
        loginBtn = findViewById(R.id.login_pg);

        /**
         * load all data from register database
         * and all data put in the array
         */
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, "https://raquib.000webhostapp.com/apps/myfoodregisterarr.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String em = jsonObject.getString("email");
                        String ps = jsonObject.getString("password");
                        array.add(em);
                        array.add(ps);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue2 = Volley.newRequestQueue(LoginActivity.this);
        queue2.add(arrayRequest);

        /**
         * start sharedpreference
         * set sharedpreference
         */
        sp = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sp.edit();

        /**
         * check sharedpreference null
         * and go to main page
         * get data sharedpreference
         */
        String ee = sp.getString("e", null);
        String pp = sp.getString("p", null);
        if (ee!=null && pp!=null && array.contains(ee) && array.contains(pp)){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        /**
         * start login button
         *
         */
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * set email and password
                 * get email and password
                 */
                String e = edEmail.getText().toString();
                String p = edPassword.getText().toString();
                /**
                 * data store in sharedpreference
                 * email, password
                 */
                editor.putString("e", e);
                editor.apply();
                editor.putString("p", p);
                editor.apply();
                /**
                 * check all condition for login
                 * email, password
                 */
                if (e.equals("") || p.equals("")){
                    Toast.makeText(LoginActivity.this, "This field is empty?", Toast.LENGTH_SHORT).show();
                }else if(!array.contains(edEmail.getText().toString()) && !e.equals("")){
                    Toast.makeText(LoginActivity.this, "Email is not match?", Toast.LENGTH_SHORT).show();
                }else if(!array.contains(edPassword.getText().toString()) && !p.equals("")){
                    Toast.makeText(LoginActivity.this, "Invalid password?", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }
}