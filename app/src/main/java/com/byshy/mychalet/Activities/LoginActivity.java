package com.byshy.mychalet.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import com.byshy.mychalet.Models.User;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.byshy.mychalet.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String EMAIL = "email";
    private static final int RC_SIGN_IN = 11;
    private static final String TAG = "task exception";
//    private static final String Google_sign_in_failed = "api_exception";
//    private static final String firebaseAuthWithGoogle = "firebaseAuthWithGoogle";
    private FirebaseAuth mAuth;
    GoogleSignInOptions gso;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("39950700111-kqjna8hcnvft6nopt79o987sd6b99crf.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if (account != null){
//            Intent homeScreen = new Intent(this, MainActivity.class);
//            startActivity(homeScreen);
//        }

                mAuth = FirebaseAuth.getInstance();

    }

    public void googleSignIn(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void emailSignUp(View view) {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }


    public void signIn(View view) {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
//                Log.w(Google_sign_in_failed, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
//        Log.d(firebaseAuthWithGoogle, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent home = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(home);

                            User user = new User(acct.getDisplayName(), acct.getEmail());

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Check your email!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                        Log.d("FirebaseDatabase", "onComplete" + task.getException().getMessage());
                                    }
                                }
                            });

                            Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_SHORT).show();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();
//                            Snackbar.make(findViewById(R.chaletName.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

}
