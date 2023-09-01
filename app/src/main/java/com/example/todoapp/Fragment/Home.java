package com.example.todoapp.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.Adapter.CollectionAdapter;
import com.example.todoapp.Interface.CollectionInterface;
import com.example.todoapp.Model.Collections;
import com.example.todoapp.Model.Todo;
import com.example.todoapp.R;
import com.example.todoapp.ViewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {
    private  EditText collectionName,collection_desc;

    boolean isBottomSheetOpen=false;
    private RecyclerView recyclerView;
    private Button add_coll_btn,save_btn;
    private CollectionInterface collectionInterface;
    private CollectionAdapter collectionAdapter;

    private HomeViewModel viewModel;

    public Home(HomeViewModel viewModel,CollectionInterface collectionInterface) {
        this.viewModel = viewModel;
        this.collectionInterface=collectionInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        init(view);

        viewModel.getList().observe(requireActivity(), new Observer<List<Collections>>() {
            @Override
            public void onChanged(List<Collections> collections) {
                updateListView(collections);
            }
        });

        add_coll_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isBottomSheetOpen)
                    showSheet();
            }
        });

        return view;
    }

    private void showSheet() {

        final Dialog dialog=new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet);

        save_btn=dialog.findViewById(R.id.collection_save_btn);
        collectionName=dialog.findViewById(R.id.collection_name);
        collection_desc=dialog.findViewById(R.id.collection_descripition);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            OnSaveBtnClicked(collectionName.getText().toString(),collection_desc.getText().toString());
            dialog.dismiss();
            }
        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.dialog_animation_style;
        dialog.show();


    }

    void init(View view){
        recyclerView=view.findViewById(R.id.home_recyclerView);
        add_coll_btn=view.findViewById(R.id.add_collection_btn);
    }

    void OnSaveBtnClicked(String name,String desc){
        Collections x= new Collections(name,desc,new ArrayList<Todo>());
        viewModel.add(x);

    }

    public void updateListView(List<Collections> collections){

        if(collectionAdapter==null) {
            collectionAdapter = new CollectionAdapter(collections,getActivity(),collectionInterface,viewModel);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(collectionAdapter);
        }
        else
           collectionAdapter.updateList(collections);
    }

}