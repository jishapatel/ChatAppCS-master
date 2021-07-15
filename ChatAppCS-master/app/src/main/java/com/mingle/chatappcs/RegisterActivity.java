package com.mingle.chatappcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    GoogleSignInClient mGoogleSignInClient;
    Button cirRegisterButton;
    EditText email,pass,uname;
    TextView alreayac;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        cirRegisterButton=findViewById(R.id.cirRegisterButton);
        email=findViewById(R.id.etEmail);
        pass=findViewById(R.id.etPassword);
        uname=findViewById(R.id.etUsername);
        alreayac = findViewById(R.id.AlreadyAccount);

        pd = new ProgressDialog(this);
        pd.setTitle("Creating Account");
        pd.setMessage("We are Creating your Account");

        //signup with google

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        alreayac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });


        cirRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                auth.createUserWithEmailAndPassword
                        (email.getText().toString(),pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.dismiss();
                        if(task.isSuccessful()){
                            Users user=new Users(uname.getText().toString(),email.getText().toString(),pass.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            startActivity(new Intent(RegisterActivity.this,ContactListActivity.class));
                            Toast.makeText(RegisterActivity.this, "User Created successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.e("Firebase Error",task.getException().toString());
                            Toast.makeText(RegisterActivity.this,task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this,ContactListActivity.class));
//        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

}