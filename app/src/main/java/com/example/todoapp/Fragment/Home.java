package com.example.todoapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.todoapp.Adapter.CollectionAdapter;
import com.example.todoapp.Model.Collections;
import com.example.todoapp.Model.Todo;
import com.example.todoapp.R;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {

    private RecyclerView recyclerView;
    private Button add_coll_btn;
    private CollectionAdapter collectionAdapter;
    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        List<Collections> list=new ArrayList<>();
        List<Todo> todolist=new ArrayList<>();
        Todo todo=new Todo("Homework","this is notes",false);

        todolist.add(todo);


        list.add(new Collections("Homework","This is best descripition",todolist));
        collectionAdapter=new CollectionAdapter(list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(collectionAdapter);
        return view;
    }
    void init(View view){
        recyclerView=view.findViewById(R.id.home_recyclerView);
        add_coll_btn=view.findViewById(R.id.add_collection_btn);
    }
}