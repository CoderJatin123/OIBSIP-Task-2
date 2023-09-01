package com.example.todoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Model.Todo;
import com.example.todoapp.R;
import com.example.todoapp.ViewModel.TodoViewModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;
    private List<Todo> todoList;
    private TodoViewModel viewModel;

    public TodoAdapter(Context context, List<Todo> todoList, TodoViewModel viewModel) {
        this.context = context;
        this.todoList = todoList;
        this.viewModel=viewModel;
    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.todo_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        Todo myTodo=todoList.get(position);
        holder.name.setText(myTodo.getTitle());

        if(todoList.get(position).getNote().trim().equals("")){
            holder.note.setText("No Notes available.");

        }else {

            holder.note.setText(myTodo.getNote());
        }

        holder.checkBox.setChecked(myTodo.isChecked());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox checkBox;
        private TextView name,note;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox=itemView.findViewById(R.id.todo_checkBox);
            name=itemView.findViewById(R.id.todo_item_name);
            note=itemView.findViewById(R.id.todo_item_note);

            checkBox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    viewModel.updateNoteChecked(getAdapterPosition());
                }
            });

        }
    }
}
