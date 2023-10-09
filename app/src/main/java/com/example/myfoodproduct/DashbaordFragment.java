package com.example.myfoodproduct;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.List;


public class DashbaordFragment extends Fragment {



    public HashMap<String, String> hashMap;
    public static int deliver_num=0, totalSum;
    private Context context;

    HashMap<String, String> mapI;
    public static ArrayList<HashMap<String, String>> itemRecalArr = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();


    HashMap<String, String> map;
    RecyclerView ctRecycle, itemRecycleView;

    Adapter adapter;
    public ItemAdapter itemAdapter;

    String bg = "#4CAF50";
    public  androidx.appcompat.widget.SearchView searchView;
    int[] ar = new int[]{0, 1, 2, 3, 4};
    int on=0;
    int pre=0, cur;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_dashbaord, container, false);
        Model.all();
        ctRecycle = v.findViewById(R.id.categoryRecyle);
        itemRecycleView = v.findViewById(R.id.itemRecycle_view);
        //searchView = v.findViewById(R.id.searchBar);
        //searchView.clearFocus();



//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterText(newText);
//                return true;
//            }
//        });



        /**
         * dashboard adapter
         *
         * use
         */
        adapter = new Adapter();
        ctRecycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ctRecycle.setAdapter(adapter);



        return v;
    }



//    private void filterText(String text) {
//        ArrayList<HashMap<String, String>> filteredList = new ArrayList<>();
//        for (int i=0; i<itemRecalArr.size(); i++){
//
//            String s = itemRecalArr.get(i).get("itname").toString().toLowerCase();
//            if (s==text.toLowerCase()){
//                map = new HashMap<>();
//                map.put("itname", s);
//                filteredList.add(map);
//            }
//        }
//
//        if (filteredList.isEmpty()) Toast.makeText(getContext(), "not found data"+list.size(), Toast.LENGTH_SHORT).show();
//        else itemAdapter.setFilteredList(filteredList);
//        /**
//        for (int i=0; i<itemRecalArr.size(); i++){
//            HashMap<String, String> hm=itemRecalArr.get(i);
//            if (hm.get("itname").toLowerCase().contains(text.toLowerCase())){
//                filteredList.add(hm.get("itname"));
//            }
//        }
//        if (filteredList.isEmpty()){
//            Toast.makeText(getContext(), "not found data", Toast.LENGTH_SHORT).show();
//        }else{
//
//        }
//         **/
//    }

    /**
     * star category adapter
     */
       public  class Adapter extends RecyclerView.Adapter<Adapter.ctView>
        {

            @NonNull
            @Override
            public ctView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View vv = layoutInflater.inflate(R.layout.layoutcatall, parent, false);
            return new ctView(vv);
        }

            @Override
            public void onBindViewHolder(@NonNull ctView holder, @SuppressLint("RecyclerView") int position) {
            map = Model.ctArr.get(position);
            holder.catImg.setImageResource(Integer.parseInt(map.get("caticon")));
            holder.catT.setText(map.get("ctname"));

            //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboardFramlay, new ItemFragment()).commit();



            holder.catLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DashbaordFragment.itemRecalArr= Model.root.get(position);
                    itemAdapter = new ItemAdapter(itemRecalArr);
                    itemRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    itemRecycleView.setAdapter(itemAdapter);





                    /**
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.cb));

                    //holder.cardView.setCardBackgroundColor(Integer.valueOf(bg));
                    //ItemFragment.topstrcatname = map.get("cat");
                    ItemFragment.itemRecalArr = Model.root.get(position);
                    Fragment mFragment = new ItemFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboardFramlay, mFragment).commit();
                   **/

                }
            });
                /**
                 * show the 0 number item
                 */
                DashbaordFragment.itemRecalArr = Model.root.get(0);
                itemAdapter = new ItemAdapter(itemRecalArr);
                itemRecycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                itemRecycleView.setAdapter(itemAdapter);





                if (on!=0){
                    holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.bg));
                }
                //Fragment mFragment = new ItemFragment();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboardFramlay, mFragment).commit();
        }




            @Override
            public int getItemCount() {
            return Model.ctArr.size();
        }

            public class ctView extends RecyclerView.ViewHolder{

                ImageView catImg;
                TextView catT;
                LinearLayout catLay;
                CardView cardView;
                public ctView(@NonNull View itemView) {
                    super(itemView);

                    catImg = itemView.findViewById(R.id.cat_img);
                    catT = itemView.findViewById(R.id.cat_t);
                    catLay = itemView.findViewById(R.id.cat_Lay);
                    cardView = itemView.findViewById(R.id.cardView);
                }
            }
        }
}