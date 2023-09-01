package com.example.todoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.Adapter.TodoAdapter;
import com.example.todoapp.Model.Collections;
import com.example.todoapp.R;
import com.example.todoapp.ViewModel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Todo extends AppCompatActivity {

    private TextView todo_title,todo_desc;
    private FloatingActionButton add_todo_btn;
    private TodoViewModel viewModel;
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        Intent intent=getIntent();
        init(intent.getLongExtra("ID",-1));

        if(viewModel!=null){
            viewModel.getCollection().observe(this, new Observer<Collections>() {
                @Override
                public void onChanged(Collections collections) {
                    todo_title.setText(collections.getName());
                    todo_desc.setText(collections.getDescription());
                }
            });

            viewModel.getTodolist().observe(this, new Observer<List<com.example.todoapp.Model.Todo>>() {
                @Override
                public void onChanged(List<com.example.todoapp.Model.Todo> todos) {
                    if(adapter==null){
                        adapter=new TodoAdapter(getBaseContext(),todos,viewModel);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        recyclerView.setAdapter(adapter);
                    }
                    else {
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }

        add_todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
    }
    private  void init(long id){

        todo_title=findViewById(R.id.todo_title);
        todo_desc=findViewById(R.id.todo_desc);
        add_todo_btn=findViewById(R.id.add_todo_btn);
        recyclerView=findViewById(R.id.todo_recyclerview);

        if(id!=-1){
            viewModel=new TodoViewModel(this,id);
        }

    }

    private void showCustomDialog(){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_todo_dialog);

        EditText name=dialog.findViewById(R.id.todo_name);
        EditText note=dialog.findViewById(R.id.todo_note);
        Button add_dialog_btn=dialog.findViewById(R.id.add_todo_dialog_btn);
        Button discard_btn=dialog.findViewById(R.id.discard_dialog_btn);

        add_dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.addTodo(new com.example.todoapp.Model.Todo(name.getText().toString(),note.getText().toString(),false));
                dialog.dismiss();
            }
        });

        discard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.dialog_animation_style;
        dialog.show();

    }
}