package com.example.myfoodproduct;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserAccountFragment extends Fragment {
    public static ArrayList<HashMap<String, String>> userArr = new ArrayList<>();
    HashMap<String, String> hashMap;
    SharedPreferences sh, sharedP;
    EditText userName, userNumber;
    TextView  update_profile;
    String nameU, numberU, id="63";


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user_account, container, false);

        userName = v.findViewById(R.id.user_name);
        userNumber = v.findViewById(R.id.user_number);
        update_profile = v.findViewById(R.id.update);

        sh = getActivity().getSharedPreferences("userData", MODE_PRIVATE);
        sharedP = getActivity().getSharedPreferences("allreadyRegister", MODE_PRIVATE);
        nameU = sh.getString("name","");
        numberU = sh.getString("number", "");



        if (nameU.equals("")) {
            nameU = sharedP.getString("name", "");
            numberU = sharedP.getString("phone", "");
            userName.setText(nameU.toUpperCase());
            userNumber.setText(numberU);
        }else{
            userName.setText(nameU.toUpperCase());
            userNumber.setText(numberU);
        }

        /**
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, "https://raquib.000webhostapp.com/apps/myfoodregisterarr.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String num = jsonObject.getString("phone");
                        hashMap = new HashMap<>();
                        hashMap.put("id", id);
                        hashMap.put("phn", num);
                        userArr.add(hashMap);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(arrayRequest);

        for (int i=0; i<userArr.size(); i++){
            hashMap = userArr.get(i);
            if (hashMap.get("phn").equals(userNumber.getText().toString())){
                id = hashMap.get("id");
                break;
            }
        }
**/
        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_up = "jack ma";
                String number_up = "333222";
                Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();

                String url = "https://raquib.000webhostapp.com/apps/userregisterupdate.php?id="+id+"&name="+name_up+"&phone="+number_up;

                StringRequest request2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        Log.d(response.toString(), "ss");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(error.toString(), "onError");
                    }
                });
                RequestQueue queue2 = Volley.newRequestQueue(getContext());
                queue2.add(request2);

            }
        });

        return  v;
    }
}