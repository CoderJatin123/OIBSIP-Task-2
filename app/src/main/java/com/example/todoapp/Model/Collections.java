package com.example.todoapp.Model;

import com.noodle.Id;

import java.util.List;

public class Collections {

    public Collections( String name,String description,List<Todo> todoList) {
        this.todoList = todoList;
        this.description = description;
        this.name = name;
    }

    @Id
    private long id;

    public long getId() {
        return id;
    }

    private List<Todo> todoList;

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description,name;
}
