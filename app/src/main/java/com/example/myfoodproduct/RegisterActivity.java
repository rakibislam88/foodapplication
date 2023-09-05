package com.example.myfoodproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    ArrayList<String> array = new ArrayList<>();
    EditText edName, edPhone;
    Button registerBtn;
    int code;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edName = findViewById(R.id.ed_name);
        edPhone = findViewById(R.id.ed_phone);
        registerBtn = findViewById(R.id.register);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, "https://raquib.000webhostapp.com/apps/myfoodregisterarr.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String ph = jsonObject.getString("phone");
                        array.add(ph);
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

        RequestQueue queue2 = Volley.newRequestQueue(RegisterActivity.this);
        queue2.add(arrayRequest);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * get name and phone number
                 */
                String n = edName.getText().toString();
                String p = edPhone.getText().toString();

                /**
                 * start opt section
                 * generate otp code
                 */
                Random random = new Random();
                code = random.nextInt(8999) + 1000;


                String registerUrl = "https://raquib.000webhostapp.com/apps/myfoodregister.php?name=" + n + "&phone="+ p+ "&otp="+code;

                if (n.equals("") ||  p.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please enter all the field", Toast.LENGTH_SHORT).show();
                }if(array.contains(edPhone.getText().toString()) && !n.isEmpty() && !p.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Phone Number are already registered", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }else if (!n.isEmpty() && !p.isEmpty()){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(getApplicationContext(), "Registration Successfully?", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, OtpVerifyActivity.class));
                            edName.setText("");
                            edPhone.setText("");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("name", n);
                            data.put("phone", p);
                            data.put("otp", String.valueOf(code));
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });


    }
}