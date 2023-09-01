package com.example.todoapp.Database;

import android.content.Context;

import com.example.todoapp.Model.Collections;
import com.noodle.Noodle;
import com.noodle.collection.Collection;

import java.util.Comparator;
import java.util.List;

public class Database {
    private Noodle noodle;
    private  Collection collection;
    private Context context;

    public Database(Context myContext){
        context=myContext;
        noodle = Noodle.with(context).addType(Collections.class).build();
        collection = noodle.collectionOf(Collections.class);
    }

    public void Add(Collections myCollection){
        collection.put(myCollection);
    }

    public List<Collections> getAllCollections(){

        List<Collections> x= collection.getAll();
        x.sort(new ListComparator());
        return  x;
    }

    public void deleteCollection(long id){
        collection.delete(id);
    }

    public void clear(){
        collection.clear();
    }

    public Collections getCollectionByID(long id){
        Collections x=(Collections) collection.get(id);
        return x;
    }

}

