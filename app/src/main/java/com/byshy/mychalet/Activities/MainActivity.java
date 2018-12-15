package com.byshy.mychalet.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.byshy.mychalet.Adapters.MyAdapter;
import com.byshy.mychalet.Models.Chalet;
import com.byshy.mychalet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mUpload;
    private DatabaseReference mDatabase;
    RecyclerView recyclerView;
    List<Chalet> ChaletList;
    MyAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUpload = findViewById(R.id.firebase);
        recyclerView = findViewById(R.id.main_activity_recycler);

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

//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("Users/" + mAuth.getCurrentUser().getUid());

        ChaletList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        for (int i = 0; i < 10; i++) {
            ChaletList.add(new Chalet("my chalet" + i, "address ", "chalet email ", i));
        }

        itemsAdapter = new MyAdapter(ChaletList, this);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Chalet chalet = dataSnapshot.getValue(Chalet.class);
                ChaletList.add(chalet);
                ChaletList.add(chalet);
                ChaletList.add(chalet);
                ChaletList.add(chalet);
                itemsAdapter.notifyDataSetChanged();
//                Toast toast = Toast.makeText(MainActivity.this, chalet.getName(), Toast.LENGTH_SHORT);
//                toast.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView.setAdapter(itemsAdapter);

    }

}
