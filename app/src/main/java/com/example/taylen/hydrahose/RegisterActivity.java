package com.example.taylen.hydrahose;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private Button btnRegister;
    private ProgressBar pgProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        pgProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "Attempting to register.");

                //Check to see if all fields have been filled in
                if(!isEmpty(txtEmail.getText().toString())
                        && !isEmpty(txtPassword.getText().toString())
                        && !isEmpty(txtConfirmPassword.getText().toString())){

                    //check if passwords match
                    if(doStringsMatch(txtPassword.getText().toString(), txtConfirmPassword.getText().toString())){
                        registerNewAccount(txtEmail.getText().toString(), txtPassword.getText().toString());
                    }else{
                        Toast.makeText(RegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisterActivity.this, "All fields are required.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //------Methods------
    //-------------------
    private void registerNewAccount(String email, String password) {
        //showDialog();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("test", "" + task.isSuccessful());

                if(task.isSuccessful()) {
                    Log.d("test", "AuthState: " + FirebaseAuth.getInstance().getCurrentUser().getUid());

                    FirebaseAuth.getInstance().signOut();
                } else {
                    Toast.makeText(RegisterActivity.this, "Unable to register", Toast.LENGTH_SHORT).show();
                }

                hideDialog();
            }
        });
    }

    private void redirectLoginScreen(){
        Log.d("test","Redirecting to login screen.");

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isEmpty(String string){
        return string.equals("");
    }

    private boolean doStringsMatch(String s1, String s2){
        return s1.equals(s2);
    }

    private void showDialog(){
        pgProgressBar.setVisibility(View.VISIBLE);

    }

    private void hideDialog(){
        if(pgProgressBar.getVisibility() == View.VISIBLE){
            pgProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
