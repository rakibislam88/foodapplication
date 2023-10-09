package com.example.myfoodproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class OtpVerifyActivity extends AppCompatActivity {

    TextView name, number, code, verifyButton;
    EditText otpEd;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static String n, p, c, otp_get="";
    TextView otp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);


        verifyButton = findViewById(R.id.verifyButton);
        otpEd = findViewById(R.id.otpEditText);
        otp = findViewById(R.id.otp);

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        otp.setText(otp_get);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ot = otpEd.getText().toString();
                if (ot.equals(c)){
                    editor.putString("name", n);
                    editor.putString("number",p);
                    editor.putString("otp", String.valueOf(code));
                    editor.apply();
                    finish();
                    String registerUrl = "https://raquib.000webhostapp.com/apps/myfoodregister.php?name=" + n + "&phone="+ p+ "&otp="+c;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, registerUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            showToast();
                            startActivity(new Intent(OtpVerifyActivity.this, MainActivity.class));
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("name", n);
                            data.put("phone", p);
                            data.put("otp", String.valueOf(c));
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }else {
                    Toast.makeText(OtpVerifyActivity.this, "Invalid OTP!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        toastText.setText("Registration Successfully?");
        //toastImage.setImageResource(R.drawable.ic_toasticon);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        toast.show();
    }
}