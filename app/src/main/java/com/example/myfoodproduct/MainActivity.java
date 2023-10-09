package com.example.myfoodproduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
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
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    public static LinearLayout layout;

    ImageView homePage, addTocartPage, wichlistPage, accountPage, user_show;
    private long pressedTime;
    SharedPreferences sh, sharedP;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.top_bar);
        homePage = findViewById(R.id.home);
        addTocartPage = findViewById(R.id.add);
        accountPage = findViewById(R.id.account);
        user_show = findViewById(R.id.u);
        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, new DashbaordFragment()).commit();
        sh = getSharedPreferences("userData", MODE_PRIVATE);
        sharedP = getSharedPreferences("allreadyRegister", MODE_PRIVATE);

        /**
        user_show.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                TextView userName, userNumber;
                userName = findViewById(R.id.name);
                //userNumber = v.findViewById(R.id.number);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.mainpopup, null, false),350,200, true);

                userName.setText("hello world!!!!!!!");

            String nameU = sh.getString("name","");
            String numberU = sh.getString("number", "");

            if (nameU.equals("")) {

                nameU = sharedP.getString("name", "");
                numberU = sharedP.getString("phone", "");
                userName.setText(nameU.toUpperCase());
                userNumber.setText(numberU);
            }else{
                userName.setText(nameU.toUpperCase());
                userNumber.setText(numberU);
            }

                pw.showAtLocation(v, Gravity.LEFT, 0, -700);

            }
        });
 **/
        /**
         * start homepage section
         */
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, new DashbaordFragment()).commit();

            }
        });

        /**
         * start add to cart page
         */
        addTocartPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, new CartFragment()).commit();
            }
        });

        /**
         * start account section
         *
         */
        accountPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.layout.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, new UserAccountFragment()).commit();
            }
        });
    }



}