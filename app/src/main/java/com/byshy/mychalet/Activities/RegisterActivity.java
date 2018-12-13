package com.byshy.mychalet.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.byshy.mychalet.Models.User;
import com.byshy.mychalet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText mFirstName;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputEditText mPasswordRepeated;
    private Button mRegister;
    private TextView mExistingAccount;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstName = findViewById(R.id.register_first_name_input);
        mEmail = findViewById(R.id.register_email_input);
        mPassword = findViewById(R.id.register_password_input);
        mPasswordRepeated = findViewById(R.id.register_repeat_password_input);
        mRegister = findViewById(R.id.register_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPassword.getText().toString().equals(mPasswordRepeated.getText().toString())) {

                    mAuth.createUserWithEmailAndPassword(mEmail.getText().toString()
                            , mPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        User user = new User(mFirstName.getText().toString().trim(),
                                                mEmail.getText().toString().trim());

                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(mAuth.getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(RegisterActivity.this, "Check your email!", Toast.LENGTH_LONG).show();
                                                        } else {
                                                            Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                                            Log.d("FirebaseDatabase", "onComplete " + task.getException().getMessage());
                                                        }
                                                    }
                                                });

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Couldn't register you, something went wrong", Toast.LENGTH_LONG).show();
                                        Log.d("FirebaseAuth", "onComplete " + task.getException().getMessage());
                                    }
                                }
                            });
                }

            }
        });

    }

}
