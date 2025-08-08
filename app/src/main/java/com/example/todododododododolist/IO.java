package com.example.todododododododolist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IO {

    MyFirstSingleton manager = MyFirstSingleton.getInstance();
    SharedPreferences prefs;
    Context ctx;

    public IO(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("todolist", Context.MODE_PRIVATE);
    }

    public void saveList(){
        SharedPreferences.Editor editor = prefs.edit();
        String json = new Gson().toJson(manager.getTodoItems());
        editor.putString("mylist", json);
        editor.apply();
    }

    public void loadList(){
        String json = prefs.getString("mylist", null);
        Type type = new TypeToken<List<TodoItem>>(){}.getType();
        List<TodoItem> items = new Gson().fromJson(json, type);
        manager.setTodoItems(items);
    }
}
