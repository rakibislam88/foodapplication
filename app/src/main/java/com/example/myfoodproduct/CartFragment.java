package com.example.myfoodproduct;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

    public static ArrayList<HashMap<String, String>> arr = new ArrayList<>();
    public  SharedPreferences sharedPreferences;
    public  SharedPreferences.Editor editor;
    HashMap<String, String> hashMap;
    RecyclerView cartRecyle;
    Adapter adapter;
    TextView subtotal, delivery_text, total, checkout;
    String deleteid;
    int total_price;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyle = v.findViewById(R.id.cartRecyle);
        total = v.findViewById(R.id.total);
        subtotal = v.findViewById(R.id.subtotal);
        checkout = v.findViewById(R.id.checkout);
        loadData();
        sharedPreferences = getContext().getSharedPreferences("lastprice", MODE_PRIVATE);
        editor = sharedPreferences.edit();



        ItemAdapter.totalSum = 0;

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://raquib.000webhostapp.com/apps/orderupdate.php?total="+ItemAdapter.totalSum;
                StringRequest orderReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue q = Volley.newRequestQueue(getContext());
                q.add(orderReq);
                startActivity(new Intent(getContext(), ShippingActivity.class));
            }
        });



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
            String id = map.get("id");
            //ItemAdapter.totalSum =0;
            ItemAdapter.totalSum += Integer.parseInt(map.get("updateprice"));
            holder.title.setText(map.get("title"));
             holder.price.setText("৳"+map.get("updateprice"));
             holder.itemNumber.setText(""+map.get("quantity"));
             total.setText("৳"+ItemAdapter.totalSum);
            //total_price = ItemAdapter.totalSum;
            /**
             * start quantity increase
             */
            holder.increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int incQun = Integer.parseInt(holder.itemNumber.getText().toString());
                    incQun++;
                    int item_price = Integer.parseInt(map.get("price").toString()) * incQun;
                    ItemAdapter.totalSum += Integer.parseInt(map.get("price").toString());
                    //total_price += Integer.parseInt(map.get("price").toString());
                    holder.itemNumber.setText(""+incQun);
                    holder.price.setText("৳"+item_price);
                    total.setText("৳"+ItemAdapter.totalSum);
                    Toast.makeText(getContext(), ""+total_price, Toast.LENGTH_SHORT).show();

                    String url = "https://raquib.000webhostapp.com/apps/myfooditempriceupdate.php?id="+id+"&quantity="+String.valueOf(incQun)+"&updateprice="+String.valueOf(item_price)+"&subtotal="+total_price+"&total="+ItemAdapter.totalSum;
                    StringRequest request1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueue queue1 = Volley.newRequestQueue(getContext());
                    queue1.add(request1);

                }
            });

            /**
             * start quantity descrease
             */
            holder.descease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int item_price=Integer.parseInt(map.get("price").toString());
                    int incQun = Integer.parseInt(holder.itemNumber.getText().toString());
                    if (incQun>1){
                        incQun--;
                        item_price = Integer.parseInt(map.get("price").toString()) * incQun;
                        ItemAdapter.totalSum -= Integer.parseInt(map.get("price").toString());
                        //total_price -= Integer.parseInt(map.get("price").toString());
                        holder.itemNumber.setText(""+incQun);
                        holder.price.setText("৳"+item_price);
                        total.setText("৳"+ItemAdapter.totalSum);
                    }
                    String url = "https://raquib.000webhostapp.com/apps/myfooditempriceupdate.php?id="+id+"&quantity="+String.valueOf(incQun)+"&updateprice="+String.valueOf(item_price)+"&subtotal="+total_price+"&total="+ItemAdapter.totalSum;
                    StringRequest request2 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueue queue2 = Volley.newRequestQueue(getContext());
                    queue2.add(request2);
                }
            });

            /**
             * start delete button
             */
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ItemAdapter.totalSum -= Integer.parseInt(map.get("updateprice"));
                    Toast.makeText(getContext(), ""+map.get("updateprice"), Toast.LENGTH_SHORT).show();


                    String url = "https://raquib.000webhostapp.com/apps/deleteandupdate.php?total=" + ItemAdapter.totalSum;
                    StringRequest request3 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    RequestQueue queue3 = Volley.newRequestQueue(v.getContext());
                    queue3.add(request3);

                    ItemAdapter.totalSum = 0;
                    String deleteitem = "https://raquib.000webhostapp.com/apps/myfooddeleteitem.php?id=" + map.get("id");
                    StringRequest request4 = new StringRequest(Request.Method.GET, deleteitem, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loadData();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    RequestQueue queue4 = Volley.newRequestQueue(getContext());
                    queue4.add(request4);



                }
            });
        }

        @Override
        public int getItemCount() {
            return arr.size();
        }

        public class cartView extends RecyclerView.ViewHolder{

            ImageView increase, descease, delete, itemimg;
            TextView title, price, itemNumber;

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

    public void loadData(){
        arr = new ArrayList<>();
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
                        String quantity = jsonObject.getString("quantity");
                        String updatepr = jsonObject.getString("updateprice");
                        String subtotal = jsonObject.getString("subtotal");
                        String total = jsonObject.getString("total");
                        String delivery = jsonObject.getString("delivery");
                        hashMap = new HashMap<>();
                        hashMap.put("id", String.valueOf(id));
                        hashMap.put("image", image);
                        hashMap.put("title", title);
                        hashMap.put("price", price);
                        hashMap.put("quantity", quantity);
                        hashMap.put("updateprice", updatepr);
                        hashMap.put("subtotal", subtotal);
                        hashMap.put("total", total);
                        hashMap.put("delivery", delivery);
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
    }
}