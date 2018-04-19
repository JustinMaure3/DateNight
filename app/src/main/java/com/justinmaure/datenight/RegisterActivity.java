package com.justinmaure.datenight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.justinmaure.datenight.Objects.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView mUsernameView;
    private EditText mEmailView;
    private EditText mPasswordView1;
    private EditText mPasswordView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView1 = (EditText) findViewById(R.id.password1);

        mPasswordView2 = (EditText) findViewById(R.id.password2);


        Button createUserBtn = (Button) findViewById(R.id.registerBtn);
        createUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take the input from all fields and checks if they are legal. if it is then create a user

                // Reset errors.
                mUsernameView.setError(null);
                mPasswordView1.setError(null);
                mPasswordView2.setError(null);
                mEmailView.setError(null);

                // Store values at the time of the login attempt.
                String username = mUsernameView.getText().toString();
                String email = mEmailView.getText().toString();
                String password1 = mPasswordView1.getText().toString();
                String password2 = mPasswordView2.getText().toString();

                View focusView = null;

                if (isUsernameValid(username) && !TextUtils.isEmpty(username)){
                    if (isPasswordValid(password1) && !TextUtils.isEmpty(password1)) {
                        if (!TextUtils.isEmpty(email)) {
                            if (password1.equals(password2)) {
                                //Create a user
                                DatabaseHelper db = new DatabaseHelper(getBaseContext());
                                User user = new User(username, password1, email);
                                db.addUser(user);
                                //Launch the login activity
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                mPasswordView1.setError("Passwords do not match");
                                focusView = mPasswordView1;
                                focusView.requestFocus();
                            }
                        } else {
                            mEmailView.setError("Please insert an email");
                            focusView = mEmailView;
                            focusView.requestFocus();
                        }
                    } else {
                        mPasswordView1.setError("Invalid Password");
                        focusView = mPasswordView1;
                        focusView.requestFocus();
                    }
                } else {
                    mUsernameView.setError("Invalid Username");
                    focusView = mUsernameView;
                    focusView.requestFocus();
                }
            }
        });

    }

    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        ArrayList<User> users = db.getAllUsers();
        db.close();
        for (int i = 0; i < users.size(); i++){
            if (username.equals(users.get(i).getUsername())) {
                return false;
            }
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }




}
