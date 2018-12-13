package com.byshy.mychalet.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.byshy.mychalet.Models.Chalet;
import com.byshy.mychalet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mUpload;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mUpload = findViewById(R.id.firebase);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Chalet chalet = new Chalet("my chalet", "address ", "chalet email ", 5);
                FirebaseDatabase.getInstance().getReference("Users/" + mAuth.getCurrentUser().getUid())
                        .child("Chalet")
                        .setValue(chalet).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Check your email!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                Log.d("FirebaseDatabase", "onComplete" + task.getException().getMessage());
                            }
                        }
                });
            }
        });
    }

}
