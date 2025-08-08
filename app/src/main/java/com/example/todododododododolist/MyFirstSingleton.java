package com.example.todododododododolist;

import java.util.ArrayList;
import java.util.List;

public class MyFirstSingleton {
    private static MyFirstSingleton instance;
    private List<TodoItem> todoItems;

    private MyFirstSingleton() {
        todoItems = new ArrayList<>();
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> list) {
        if (list != null)
            todoItems = list;
    }

    public void addTodoItem(TodoItem todoItem) {
        todoItems.add(todoItem);
    }

    public void removeTodoItem(int index) {
        if (index >= 0 && index < todoItems.size())
            todoItems.remove(index);
    }

    public void clearList() {
        todoItems.clear();
    }


    public static synchronized MyFirstSingleton getInstance() {
        if (instance == null) instance = new MyFirstSingleton();
        return instance;
    }

}
