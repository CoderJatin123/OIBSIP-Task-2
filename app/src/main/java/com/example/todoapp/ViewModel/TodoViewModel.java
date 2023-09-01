package com.example.todoapp.ViewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoapp.Model.Collections;
import com.example.todoapp.Model.Todo;
import com.example.todoapp.Repository.TodoRepository;

import java.io.Closeable;
import java.util.List;

public class TodoViewModel extends ViewModel {

   public MutableLiveData<Collections> collection;
   private Context context;
   public MutableLiveData<List<Todo>> todolist;
   private TodoRepository repository;

    public TodoViewModel(Context context,long id) {
        todolist=new MutableLiveData<>();
        collection=new MutableLiveData<>();
       repository=new TodoRepository(context);
       collection.setValue(repository.getCollectionById(id));
       this.todolist.setValue(collection.getValue().getTodoList());
    }

    public LiveData<List<Todo>> getTodolist() {
        return todolist;
    }

    public void addTodo(Todo todo){

        collection.getValue().getTodoList().add(todo);
        todolist.setValue(collection.getValue().getTodoList());
        repository.update(collection.getValue());
    }

    public LiveData<Collections> getCollection(){
        return collection;
    }

    public void updateNoteChecked(int position){

        todolist.getValue().get(position).setChecked(!todolist.getValue().get(position).isChecked());
        collection.getValue().setTodoList(todolist.getValue());
        repository.update(collection.getValue());
    }
}

