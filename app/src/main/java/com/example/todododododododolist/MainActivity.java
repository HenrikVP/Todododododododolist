package com.example.todododododododolist;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //TODO Make list in singleton
    //List<TodoItem> todoList;

    SharedPreferences prefs = getApplicationContext().getSharedPreferences("todolist", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();
    MyFirstSingleton manager = MyFirstSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // TODO Load list from device
        //todoList = new ArrayList<>();
        Intent getFromCreateIntent = getIntent();
        if (getFromCreateIntent != null)
        {
            TodoItem todoItem = (TodoItem) getFromCreateIntent.getSerializableExtra("TodoItem");
            manager.addTodoItem(todoItem);
            //TODO SaveList
        }


        findViewById(R.id.fob_add).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
            startActivity(intent);

        });
    }

    public void saveList(){
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