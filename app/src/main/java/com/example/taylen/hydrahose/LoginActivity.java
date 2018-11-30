package com.example.taylen.hydrahose;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    //private View root;
    private EditText txtEmail;
    private EditText txtPassword;
    private ProgressBar pgProgressBar;

    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
        pgProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        setupFirebaseAuth();

        Button logIn = (Button) findViewById(R.id.btnLogin);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make sure all fields are filled out first
                if(!isEmpty(txtEmail.getText().toString()) && !isEmpty(txtPassword.getText().toString())) {
                    Log.d("test", "Attempting to authenticate.");

                    closeKeyboard();
                    showDialog();

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(txtEmail.getText().toString(),
                            txtPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //TODO: After login successful, navigate to the product screen
                                    Log.d("test", "Login was successful.");
                                    hideDialog();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            //hideDialog();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView register = (TextView) findViewById(R.id.lnkRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        TextView resetPassword = (TextView) findViewById(R.id.lnkForgotPassword);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement event handling
                Toast.makeText(LoginActivity.this, "Forgot", Toast.LENGTH_SHORT).show();
            }
        });

        TextView resendEmail = (TextView) findViewById(R.id.lnkGuestBrowse);
        resendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement event handling
                Toast.makeText(LoginActivity.this, "Guest.", Toast.LENGTH_SHORT).show();
            }
        });

        //hideKeyboard();
    }


    //--------Methods----------
    //-------------------------
    private boolean isEmpty(String string) {
        return string.equals("");
    }

    private void showDialog() {
        pgProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideDialog() {
        if(pgProgressBar.getVisibility() == View.VISIBLE) {
            pgProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //--------Firebase Setup-----------
    private void setupFirebaseAuth() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null) {
                    if(user.isEmailVerified()){
                        Log.d("test", user.getUid().toString());
                        Toast.makeText(LoginActivity.this, "Authenticated with: " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Check your email for a verificiation link", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }

                } else {
                    Log.d("test", "Signed out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        if(mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}
