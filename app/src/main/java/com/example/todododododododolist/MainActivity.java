package com.example.todododododododolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    MyFirstSingleton manager;

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
        manager = MyFirstSingleton.getInstance();
        //Loads list from shared prefs.
        new IO(getApplicationContext()).loadList();
        //Gets todoitem that have just been created
        Intent getFromCreateIntent = getIntent();
        if (getFromCreateIntent != null)
        {
            TodoItem todoItem = (TodoItem) getFromCreateIntent.getSerializableExtra("TodoItem");
            manager.getTodoItems();
            if (todoItem != null) manager.addTodoItem(todoItem);
            //Saves list in shared prefs
            new IO(getApplicationContext()).saveList();
        }


        findViewById(R.id.fob_add).setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateActivity.class);
            startActivity(intent);

        });
    }
}