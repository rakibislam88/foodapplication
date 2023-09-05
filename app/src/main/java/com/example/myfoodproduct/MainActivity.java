package com.example.myfoodproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView homePage, addTocartPage, wichlistPage, accountPage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homePage = findViewById(R.id.home);
        addTocartPage = findViewById(R.id.add);
        accountPage = findViewById(R.id.account);
        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, new DashbaordFragment()).commit();


        /**
         * start homepage section
         */
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framLayout, new DashbaordFragment()).commit();

            }
        });

        /**
         * start add to cart page
         */
        addTocartPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

            }
        });
    }
}