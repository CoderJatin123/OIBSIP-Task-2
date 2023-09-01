package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.todoapp.Activity.Todo;
import com.example.todoapp.Authantication.AuthActivity;
import com.example.todoapp.Fragment.Home;
import com.example.todoapp.Interface.CollectionInterface;
import com.example.todoapp.ViewModel.HomeViewModel;
import com.example.todoapp.ViewModel.TodoViewModel;

public class MainActivity extends AppCompatActivity implements CollectionInterface {
    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private Home home_fragment;
    private HomeViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        viewModel = new HomeViewModel(this);
        fragmentManager = getSupportFragmentManager();
        home_fragment=new Home(viewModel,this);
        loadFragment(home_fragment);
        toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

    }

    private void loadFragment(Fragment x){

        FragmentTransaction ft= fragmentManager.beginTransaction();
        ft.add(R.id.main_frame,x);
        ft.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case  R.id.toolbar_clear_collections :
                viewModel.clear();
                break;
            case R.id.toolbar_logout:
                    logout();
                    break;
            case R.id.toolbar_delete:
                viewModel.setIsSelectionEnabled(true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){

        Intent intent=new Intent(this, AuthActivity.class);
        Bundle x = new Bundle();
        x.putString("TAG","TAG_LOGOUT");
        intent.putExtras(x);
        startActivity(intent);
    }

    @Override
    public void onCollectionSelected(long id) {

        Intent x= new Intent(this, Todo.class);
        Bundle y=new Bundle();
        y.putLong("ID",id);
        x.putExtras(y);
        startActivity(x);
    }
}