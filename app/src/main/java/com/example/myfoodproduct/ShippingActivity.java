package com.example.myfoodproduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ShippingActivity extends AppCompatActivity {



    public static ArrayList<HashMap<String, String>> orderArr = new ArrayList<>();
    HashMap<String, String> hashMap;
    RecyclerView order_Recycle;
    OrderAdapter adapter;
    EditText name, phone, address;
    TextView subtotal, allDel, total, confirmBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.ed_address);
        subtotal = findViewById(R.id.subtotal);
        total  = findViewById(R.id.total);
        order_Recycle = findViewById(R.id.order_recycle);
        confirmBtn = findViewById(R.id.confirm_btn);


        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences sharedP = getSharedPreferences("allreadyRegister", MODE_PRIVATE);
        String nm = sharedPreferences.getString("name", "");
        if (nm.equals("")){
            name.setText(sharedP.getString("name", ""));
            phone.setText(sharedP.getString("phone", ""));
        }else{
            name.setText(nm);
            phone.setText(sharedPreferences.getString("number", ""));
        }


        adapter = new OrderAdapter();
        order_Recycle.setLayoutManager(new LinearLayoutManager(ShippingActivity.this));
        order_Recycle.setAdapter(adapter);

        subtotal.setText("৳"+CartFragment.arr.get(0).get("total"));
        total.setText("৳"+CartFragment.arr.get(0).get("total"));
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_address = address.getText().toString();
            }
        });
    }

    public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewOrder>{

        @NonNull
        @Override
        public viewOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View vv = layoutInflater.inflate(R.layout.orderlayout, parent, false);
            return new viewOrder(vv);
        }

        @Override
        public void onBindViewHolder(@NonNull viewOrder holder, int position) {

            hashMap = CartFragment.arr.get(position);
            holder.name_quantity.setText(hashMap.get("quantity")+"× "+hashMap.get("title"));
            holder.item_price.setText("৳"+hashMap.get("updateprice"));
        }

        @Override
        public int getItemCount() {
            return CartFragment.arr.size();
        }

        public class viewOrder extends RecyclerView.ViewHolder{
            TextView name_quantity, item_price;
            public viewOrder(@NonNull View itemView) {
                super(itemView);
                name_quantity = itemView.findViewById(R.id.item_name_quantity);
                item_price = itemView.findViewById(R.id.item_price);
            }
        }

    }
}