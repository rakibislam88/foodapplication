package com.example.myfoodproduct;

import static com.example.myfoodproduct.Model.hashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.security.AccessController;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.itemAd> {



    private ArrayList<HashMap<String, String>> itemRecalArr = new ArrayList<>();
    public HashMap<String, String> hashMap;
    public static int deliver_num=0, totalSum = 0;
    private Context context;

    HashMap<String, String> mapI;




    public ItemAdapter(ArrayList<HashMap<String, String>> itemRecalArr) {
        this.itemRecalArr = itemRecalArr;
    }

    @NonNull
    @Override
    public itemAd onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new itemAd(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemAd holder, @SuppressLint("RecyclerView") int position) {


        mapI = itemRecalArr.get(position);
        String price = mapI.get("price");
        String title = mapI.get("itname");
        Picasso.get()
                .load(mapI.get("itimg"))
                .into(holder.itemImg);
        holder.itemT.setText(title);
        holder.itemP.setText("৳"+price);

        /**
         * start add item button in database
         *
         */
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliver_num+=1;
                totalSum += Integer.parseInt(price);
                Toast.makeText(v.getContext(), "add to cart"+deliver_num, Toast.LENGTH_SHORT).show();
                //showToast();
                String itemUrl = "https://raquib.000webhostapp.com/apps/myfooditem.php?image=" + mapI.get("itimg") +"&title="+ title+"&price="+price+"&delivery="+deliver_num+"&updateprice="+price+"&total="+totalSum;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, itemUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
                requestQueue.add(stringRequest);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemRecalArr.size();
    }

    public class itemAd extends RecyclerView.ViewHolder{

        ImageView itemImg;
        TextView itemT, itemP, add;
        LinearLayout layout;
        public itemAd(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_img);
            itemT = itemView.findViewById(R.id.item_t);
            itemP = itemView.findViewById(R.id.item_p);
            add = itemView.findViewById(R.id.add);
            layout = itemView.findViewById(R.id.toast_root);
        }
    }


//    public void showToast() {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View layout = inflater.inflate(R.layout.toast_custom,  );
//        TextView toastText = layout.findViewById(R.id.toast_text);
//        toastText.setText("this!");
//        //toastImage.setImageResource(R.drawable.ic_toasticon);
//        Toast toast = new Toast(layout.getContext());
//        toast.setGravity(Gravity.BOTTOM, 0, 0);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(layout);
//        toast.show();
//    }
}
