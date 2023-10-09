package com.example.myfoodproduct;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemFragment extends Fragment {

   /**
    public static ArrayList<HashMap<String, String>> itemRecalArr = new ArrayList<>();
    HashMap<String, String> mapI;
    RecyclerView itemRecycle;
    public static Adapter adapter;
    **/
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item, container, false);





       /**
        itemRecycle = v.findViewById(R.id.ctItemRecyle);
        adapter = new Adapter(itemRecalArr);


        /**
         * use item recycle view
         */
       /**
        itemRecycle.setLayoutManager(new GridLayoutManager(getContext(), 2));
        itemRecycle.setAdapter(adapter);
        **/
        return  v;
    }

//
//    public class Adapter extends RecyclerView.Adapter<Adapter.itemView>{
//
//
//
//        public Adapter(ArrayList<HashMap<String, String>> itemList){
//
//        }
//        public void setFilteredList(List<String> filteredList){
//            notifyDataSetChanged();
//        }
//
//        @NonNull
//        @Override
//        public itemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = getLayoutInflater();
//            View vv = layoutInflater.inflate(R.layout.itemlayout, parent, false);
//            return new itemView(vv);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull itemView holder, int position) {
//
//            mapI = itemRecalArr.get(position);
//            Picasso.get()
//                    .load(mapI.get("itimg"))
//                    .into(holder.itemImg);
//            holder.itemT.setText(mapI.get("itname"));
//            holder.itemP.setText("৳"+mapI.get("price"));
//
//            /**
//             * start add item button in database
//             *
//             */
//            holder.add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(), mapI.get("price"), Toast.LENGTH_SHORT).show();
//                    String itemUrl = "https://raquib.000webhostapp.com/apps/myfooditem.php?image=" + mapI.get("itimg") +"&title="+ mapI.get("itname")+"&price="+mapI.get("price");
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, itemUrl, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    });
//                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//                    requestQueue.add(stringRequest);
//
//                }
//            });
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return itemRecalArr.size();
//        }
//
//        public class itemView extends RecyclerView.ViewHolder{
//
//            ImageView itemImg;
//            TextView itemT, itemP, add;
//            public itemView(@NonNull View itemView) {
//                super(itemView);
//                itemImg = itemView.findViewById(R.id.item_img);
//                itemT = itemView.findViewById(R.id.item_t);
//                itemP = itemView.findViewById(R.id.item_p);
//                add = itemView.findViewById(R.id.add);
//            }
//        }
//    }
}