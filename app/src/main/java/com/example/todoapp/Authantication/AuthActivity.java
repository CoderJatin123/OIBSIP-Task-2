package com.example.todoapp.Authantication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.todoapp.Authantication.Login.Login;
import com.example.todoapp.Authantication.Signup.Signup;
import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.User;
import com.example.todoapp.R;

public class AuthActivity extends AppCompatActivity {

    private boolean isAuth=false;
    private FragmentManager fragmentManager;
    private Login login;
    private Signup signup;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        init(this);
        if(!isAuth){
            login=new Login();
            loadFragment(login);
        }
    }

    private void init(Activity activity){
        fragmentManager= getSupportFragmentManager();
    sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
    editor = sharedPref.edit();
    }

    private boolean checkAuth() {
     String email=sharedPref.getString("User_email",null);
     String pass=sharedPref.getString("User_pass",null);

     if(email==null || pass==null){
         return false;
     }
     else {
         User x=login.getCredential();
           if(email.equals(x.getEmail())){
               if(pass.equals(x.getPassword()))
                   return true;
               else {
                   login.PasswordError();
                   return false;
               }
           }
           else {
               login.EmailError();
               return false;
           }
     }
    }


    private void loadFragment(Fragment x){

            FragmentTransaction ft= fragmentManager.beginTransaction();
            ft.replace(R.id.auth_frame,x);
            ft.commit();
    }

    public void loadSignup(){
        if(signup==null)
                signup=new Signup();
        loadFragment(signup);
    }

    public void loadLogin(){
        if(login == null)
            login=new Login();
        loadFragment(login);
    }

    public void createUser(String name, String email, String pass){

        editor.putString("User_name", name);
        editor.putString("User_email",email);
        editor.putString("User_pass",pass);
        editor.apply();
        OnAuthCompleted();
    }



    void OnAuthCompleted(){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        activity.finish();
    }

    public void onLogin(){
        if(checkAuth()){
            OnAuthCompleted();
        }
    }
}