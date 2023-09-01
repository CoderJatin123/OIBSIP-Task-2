package com.example.todoapp.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoapp.Model.Collections;
import com.example.todoapp.Repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private Context context;
    public MutableLiveData<List<Collections>> list;
    private TodoRepository repository;
    private MutableLiveData<Boolean> isSelectionEnabled;



    public HomeViewModel(Context context) {
        this.context = context;
        repository=new TodoRepository(context);
        list=new MutableLiveData<>();
        list.setValue(repository.getAll());
        isSelectionEnabled=new MutableLiveData<>();
        isSelectionEnabled.setValue(false);
    }

    public LiveData<List<Collections>> getList(){
        return list;
    }

    public void updateCollection(Collections collections){
        repository.update(collections);
        list.setValue(repository.getAll());
    }

    public void delete(long id){
        repository.delete(id);
        list.setValue(repository.getAll());
    }

    public void add(Collections collection){
     repository.update(collection);
     list.setValue(repository.getAll());
    }

    public void clear(){
        repository.Clear();
        list.setValue(repository.getAll());
    }

    public LiveData<Boolean> getIsSelectionEnabled() {
        return isSelectionEnabled;
    }

    public void setIsSelectionEnabled(boolean isSelectionEnabled) {
        this.isSelectionEnabled.setValue(isSelectionEnabled);
    }

}

