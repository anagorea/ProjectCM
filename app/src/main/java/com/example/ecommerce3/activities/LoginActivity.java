package com.example.ecommerce3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ecommerce3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button LoginButton;
    EditText InputEmail, InputPassword;
    ProgressDialog loadingBar;

    FirebaseAuth auth;
    FirebaseDatabase database;

   // private String parentDbName = "Users";
   //private CheckBox chkBoxRememberMe;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        checkBox();

        LoginButton = findViewById(R.id.login_btn);
        InputEmail = findViewById(R.id.login_email_input);
        InputPassword = findViewById(R.id.login_password_input);
       // AdminLink = (TextView) findViewById(R.id.admin_panel_link);
       // NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);
        loadingBar = new ProgressDialog(this);






        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });
    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name", "");
        if(check.equals("true")){
            Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    private void LoginUser()
    {
        String email = InputEmail.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            //AllowAccessToAccount(email, password);
        }

                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful())
                                        {
                                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("name", "true");
                                            editor.apply();

                                            Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(LoginActivity.this, "Error" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


//                        if(task.isSuccessful())
//                        {
//
//                          // String id = task.getResult().getUser().getUid();
//                           // database.getReference().child("Users").child(id).setValue(userModel);
//
//                            Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
//
////                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
////                            startActivity(intent);
//
//                        }
//                        else
//                        {
//                            Toast.makeText(LoginActivity.this, "Error" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
//
//                        }
                    }
//                });
    }

//    private void AllowAccessToAccount(final String email, final String password) {
//        final DatabaseReference RootRef;
//        RootRef = FirebaseDatabase.getInstance().getReference();
//
//
//        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot)
//            {
//                if (datasnapshot.child(parentDbName).child(email).exists())
//                {
//                    UserModel userdata = datasnapshot.child(parentDbName).child(email).getValue(UserModel.class);
//
//                    if (userdata.getEmail().equals(email))
//                    {
//                        if (userdata.getPassword().equals(password))
//                        {
//                            Toast.makeText(LoginActivity.this, "logged in Succesfully...", Toast.LENGTH_SHORT).show();
//                            loadingBar.dismiss();
//
//                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                            startActivity(intent);
//                        }
//                        else
//                        {
//                            loadingBar.dismiss();
//                            Toast.makeText(LoginActivity.this, "Password is incorrect...", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this, "Account with this" + email + "email do not exists.", Toast.LENGTH_SHORT).show();
//                    loadingBar.dismiss();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

