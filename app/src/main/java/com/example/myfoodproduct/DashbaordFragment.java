package com.example.myfoodproduct;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class DashbaordFragment extends Fragment {



    HashMap<String, String> map;
    RecyclerView ctRecycle, recyleT;

    Adapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_dashbaord, container, false);
        Model.all();
        ctRecycle = v.findViewById(R.id.categoryRecyle);




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
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboardFramlay, new ItemFragment()).commit();
            holder.catLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //ItemFragment.topstrcatname = map.get("cat");
                    ItemFragment.itemRecalArr = Model.root.get(position);
                    Fragment mFragment = new ItemFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.dashboardFramlay, mFragment).commit();
                }
            });
        }

            @Override
            public int getItemCount() {
            return Model.ctArr.size();
        }

            public class ctView extends RecyclerView.ViewHolder{

                ImageView catImg;
                TextView catT;
                LinearLayout catLay;
                public ctView(@NonNull View itemView) {
                    super(itemView);

                    catImg = itemView.findViewById(R.id.cat_img);
                    catT = itemView.findViewById(R.id.cat_t);
                    catLay = itemView.findViewById(R.id.cat_Lay);
                }
            }
        }

}