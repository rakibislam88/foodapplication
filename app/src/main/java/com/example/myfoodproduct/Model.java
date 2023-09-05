package com.example.myfoodproduct;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;

public class Model {

    public static ArrayList<ArrayList<HashMap<String, String>>> root = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> itemArr = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> ctArr = new ArrayList<>();
    public static HashMap<String, String> hashMap;

    /**
     *
     * @param itemImg
     * @param itemName
     * @param itemDesc
     * @param price
     * @param km
     * @param min
     * @param grm
     * @param addtocart
     */
    public static void ctItem(String itemImg, String itemName, String itemDesc, String price, String km, String min, String grm, String addtocart){
        hashMap = new HashMap<>();
        hashMap.put("itimg", itemImg);
        hashMap.put("itname", itemName);
        hashMap.put("itdesc", itemDesc);
        hashMap.put("price", price);
        hashMap.put("itkm", km);
        hashMap.put("itmin", min);
        hashMap.put("itgrm", grm);
        hashMap.put("itadd", addtocart);
        itemArr.add(hashMap);
    }

    /**
     *
     * @param ctName
     * @param catIcon
     */
    public static void category(String ctName, int catIcon){
        root.add(itemArr);
        hashMap = new HashMap<>();
        hashMap.put("ctname", ctName);
        hashMap.put("caticon", String.valueOf(catIcon));
        ctArr.add(hashMap);
        itemArr = new ArrayList<>();
    }


    public static void all(){
        ctArr =new ArrayList<>();
        ctItem("https://d1nfw7b4288zmf.cloudfront.net/shop/img/cover/villagepizzaburgerpastahalalbremen/af80ff61bac7ae4d.jpg", "Chicken Burger", "item description", "200", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "Chicken Burger", "item description", "100", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "Chicken Burger", "item description", "10", "2km","30-32min", "30g", "Add To Cart");
        category("Burger", R.drawable.burger);

        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "pizza", "item description", "100", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "pizza", "item description", "10", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "pizza", "item description", "10", "2km","30-32min", "30g", "Add To Cart");
        category("Pizza", R.drawable.pizza);

        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "sallad", "item description",  "100", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "sallad", "item description", "10", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "sallad", "item description", "12", "2km","30-32min", "30g", "Add To Cart");
        category("Sallad", R.drawable.salad);

        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "noodle", "item description", "12", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "noodle", "item description", "14", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "noodle", "item description", "15", "2km","30-32min", "30g", "Add To Cart");
        category("Noodle", R.drawable.noodles);

        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "Rice", "item description", "15", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "Rice", "item description", "13", "2km","30-32min", "30g", "Add To Cart");
        ctItem("https://images.unsplash.com/photo-1568901346375-23c9450c58cd?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YnVyZ2VyfGVufDB8fDB8fHww&w=1000&q=80", "Rice", "item description", "23", "2km","30-32min", "30g", "Add To Cart");
        category("Rice", R.drawable.rice);
    }
}
