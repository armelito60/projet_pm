package com.example.projet_pm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Racine {
    Gson gson = new Gson();
    private Type userListType = new TypeToken<ArrayList<Object>>(){}.getType();
    ArrayList<Object> userArray = gson.fromJson((JsonReader) userListType, Racine.class);


    Racine() {
        for(Object m : userArray) {
            userArray.add(new Match());
        }
    }
    public List<Object> getFootball() {
        return userArray;
    }
}
