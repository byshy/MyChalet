package com.byshy.mychalet.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.byshy.mychalet.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void register(View view) {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }


    public void signin (View view) {
        Intent register = new Intent(this, MainActivity.class);
        startActivity(register);
    }
}
