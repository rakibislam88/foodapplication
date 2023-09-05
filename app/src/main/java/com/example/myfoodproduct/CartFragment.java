package com.example.myfoodproduct;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class CartFragment extends Fragment {

    ArrayList<HashMap<String, String>> arr = new ArrayList<>();
    HashMap<String, String> hashMap;
    RecyclerView cartRecyle;
    Adapter adapter;
    int p=0, mValue=0, ip=0, t=0;
    EditText total;
    String deleteid;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyle = v.findViewById(R.id.cartRecyle);
        total = v.findViewById(R.id.total);


        /**
         * item data loaded
         */
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, "https://raquib.000webhostapp.com/apps/myfooditemarr.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = Integer.parseInt(jsonObject.getString("id"));
                        deleteid = String.valueOf(id);
                        String image = jsonObject.getString("image");
                        String title = jsonObject.getString("title");
                        String price = jsonObject.getString("price");
                        hashMap = new HashMap<>();
                        hashMap.put("id", String.valueOf(id));
                        hashMap.put("image", image);
                        hashMap.put("title", title);
                        hashMap.put("price", price);
                        arr.add(hashMap);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }

                adapter = new Adapter();
                cartRecyle.setLayoutManager(new LinearLayoutManager(getContext()));
                cartRecyle.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue2 = Volley.newRequestQueue(getContext());
        queue2.add(arrayRequest);

        deleteofFun();
        return  v;
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.cartView>{

        @NonNull
        @Override
        public cartView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View vv = layoutInflater.inflate(R.layout.cartlayout, parent, false);
            return new cartView(vv);
        }

        @Override
        public void onBindViewHolder(@NonNull cartView holder, int position) {
            HashMap<String, String> map= arr.get(position);
            Picasso.get().load(hashMap.get("image")).into(holder.itemimg);
            holder.title.setText(map.get("title"));
            holder.price.setText("৳"+map.get("price"));

            /**
             * start quantity increase
             */
            holder.increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p = Integer.parseInt(map.get("price"));
                    mValue = Integer.parseInt(holder.itemNumber.getText().toString());
                    mValue++;
                    holder.itemNumber.setText(""+mValue);
                    ip=p*mValue;
                    holder.price.setText("৳"+ip);
                    cartupdate();
                }
            });

            /**
             * start quantity descrease
             */
            holder.descease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mValue>0) {
                        mValue--;
                        ip-=p;
                    }

                    holder.itemNumber.setText(""+mValue);
                    holder.price.setText("৳"+ip);

                    if (t>0 && t>-0) t-=p;
                    total.setText(""+t);
                }
            });

            /**
             * start delete button
             */
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteofFun();
                }
            });
        }

        @Override
        public int getItemCount() {
            return arr.size();
        }

        public class cartView extends RecyclerView.ViewHolder{

            ImageView increase, descease, delete, itemimg;
            TextView title, price;
            EditText itemNumber;
            public cartView(@NonNull View itemView) {
                super(itemView);

                increase = itemView.findViewById(R.id.item_increase);
                descease = itemView.findViewById(R.id.item_decrease);
                itemimg = itemView.findViewById(R.id.item_image);
                title = itemView.findViewById(R.id.title);
                price = itemView.findViewById(R.id.price);
                itemNumber = itemView.findViewById(R.id.item_number);
                delete = itemView.findViewById(R.id.item_close);
            }
        }
    }

    private void cartupdate() {
        t= Integer.parseInt(total.getText().toString());
        t = t+p;
        total.setText(""+t);
    }

    void deleteofFun(){
        String deleteitem = "https://raquib.000webhostapp.com/apps/myfooddeleteitem.php?id=" + deleteid;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, deleteitem, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}