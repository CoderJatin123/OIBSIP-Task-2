package com.example.todoapp.Repository;

import android.content.Context;

import com.example.todoapp.Database.Database;
import com.example.todoapp.Model.Collections;

import java.util.List;

public class TodoRepository {
    private Context context;
    private Database database;

    public TodoRepository(Context context) {
        this.context = context;
        database=new Database(context);
    }

    public void update(Collections collection){
        database.Add(collection);
    }

    public void delete(long id){
        database.deleteCollection(id);
    }

    public List<Collections> getAll(){
       return database.getAllCollections();
    }

    public void Clear(){
        database.clear();
    }

    public Collections getCollectionById(long id){
       return database.getCollectionByID(id);
    }
}
