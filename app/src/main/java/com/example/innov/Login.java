package com.example.innov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;

public class Login extends AppCompatActivity {
    FirebaseAuth authi;
    EditText x,y;
    Button m;
    TextView t,t1;
    FileInputStream fstream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authi=FirebaseAuth.getInstance();
        if (authi.getCurrentUser()!=null) {
            try {
                fstream=openFileInput("userdetails22");
                switch (fstream.read()) {
                    case 1:
                        fstream.close();
                        Intent j = new Intent(Login.this,Customer.class);
                        startActivity(j);
                        finish();
                        break;
                    case 2:
                        fstream.close();
                        Intent k = new Intent(Login.this,Seller.class);
                        startActivity(k);
                        finish();
                        break;
                    default:
                        Intent l = new Intent(Login.this,Customer.class);
                        startActivity(l);
                        finish();
                }
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error Occurreddddddddddd " + e, Toast.LENGTH_LONG).show();
            }

        }
        else {
            setContentView(R.layout.activity_login);
            t=findViewById(R.id.forgotpassword);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent k = new Intent(Login.this, Forgot.class);
                    startActivity(k);
                }
            });
            x = findViewById(R.id.inputemail);
            y = findViewById(R.id.inputpassword);
            m = findViewById(R.id.btnlogin);
            t1 =findViewById(R.id.gotoRegister);
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent j = new Intent(Login.this,Registration.class);
                    startActivity(j);
                }
            });
            m.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = x.getText().toString().trim();
                    String password = y.getText().toString().trim();
                    if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
                    {
                        Toast.makeText(getApplicationContext(), "please enter the required fields", Toast.LENGTH_LONG).show();
                    }
                    else{
                        authi.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if ((!task.isSuccessful())) {
                                    Toast.makeText(getApplicationContext(), "error occured while signing", Toast.LENGTH_LONG).show();
                                } else {
                                    try {
                                        fstream = openFileInput("userdetails22");
                                        switch (fstream.read()) {
                                            case 1:
                                                fstream.close();
                                                Intent j = new Intent(Login.this, Seller.class);
                                                startActivity(j);
                                                finish();
                                                break;
                                            case 2:
                                                fstream.close();
                                                Intent k = new Intent(Login.this, Customer.class);
                                                startActivity(k);
                                                finish();
                                                break;
                                            default:
                                                Intent l = new Intent(Login.this, Customer.class);
                                                startActivity(l);
                                                finish();
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Error Occurreddddddddddd " + e, Toast.LENGTH_LONG).show();
                                    }
                                }

                            }


                        });
                    }

                }
            });
        }
    }

}
