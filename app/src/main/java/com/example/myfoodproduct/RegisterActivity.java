package com.example.myfoodproduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class RegisterActivity extends AppCompatActivity {

    public ArrayList<String> array = new ArrayList<>();
    EditText edName, edPhone;
    TextView registerBtn;
    String pp, otpMsg="is your verification code?";

    String n, p, tr="true";
    public static SharedPreferences sharedP;
    public static SharedPreferences.Editor spEdit;
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
                //Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue2 = Volley.newRequestQueue(RegisterActivity.this);
        queue2.add(arrayRequest);

        sharedP = getSharedPreferences("allreadyRegister", MODE_PRIVATE);
        spEdit = sharedP.edit();


        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences ss = getSharedPreferences("allreadyRegister", MODE_PRIVATE);
        pp = sharedPreferences.getString("number", "");
        if (pp.equals("")){
            pp = ss.getString("phone", "");
        }


        if (!pp.equals("")){
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        }


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * get name and phone number
                 */
                n = edName.getText().toString();
                p = edPhone.getText().toString();
                //String phone = "+91" + edtPhone.getText().toString();


                /**
                 * start opt section
                 * generate otp code
                 */
                Random random = new Random();
                code = random.nextInt(8999) + 1000;



                if (n.equals("") ||  p.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill up all field", Toast.LENGTH_SHORT).show();
                }if(array.contains(edPhone.getText().toString()) && !n.isEmpty() && !p.isEmpty()){
                    showToast();
                    spEdit.putString("name", n);
                    spEdit.putString("phone", p);
                    spEdit.apply();
                    //Toast.makeText(RegisterActivity.this, ""+p+tr, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }if (p.length()!=11){
                    Toast.makeText(RegisterActivity.this, "Number is INVLID", Toast.LENGTH_SHORT).show();
                }else if (!n.isEmpty() && !p.isEmpty()){
                    OtpVerifyActivity.n = n;
                    OtpVerifyActivity.p = p;
                    OtpVerifyActivity.c = String.valueOf(code);
                    OtpVerifyActivity.otp_get = String.valueOf(code);
                    startActivity(new Intent(RegisterActivity.this, OtpVerifyActivity.class));
                    edName.setText("");
                    edPhone.setText("");
                }
            }
        });

    }



    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText("This Phone Number Are Already Registered!");
        //toastImage.setImageResource(R.drawable.ic_toasticon);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        toast.show();
    }
}