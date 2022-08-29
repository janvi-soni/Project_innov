package com.example.innov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileOutputStream;

public class Registration extends AppCompatActivity {
    EditText one,two,three;
    Button four;
    FirebaseAuth authiiii;
    CheckBox cbs,cbt;
    FileOutputStream fstream;
    TextView t;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        authiiii=FirebaseAuth.getInstance();
        one=findViewById(R.id.inputemail);
        two=findViewById(R.id.inputpassword);
        three=findViewById(R.id.confirmpassword);
        cbs=findViewById(R.id.cbs);
        cbt=findViewById(R.id.cbt);

        filemethod();
        cbt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if(compoundButton.isChecked())
                {
                    cbs.setChecked(false);
                }
            }
        });
        cbs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,boolean b) {
                if(compoundButton.isChecked())
                {
                    cbt.setChecked(false);
                }
            }
        });
        t=findViewById(R.id.gotologin);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u=new Intent(Registration.this,Login.class);
                startActivity(u);
            }
        });
        four=findViewById(R.id.btnsignup);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbs.isChecked())
                {
                    count=1;
                    filemethod();

                }
                if (cbt.isChecked())
                {
                    count=2;
                    filemethod();

                }
                String emailit=one.getText().toString().trim();
                String passwordit=two.getText().toString().trim();
                String confirmit=three.getText().toString().trim();

                if (TextUtils.isEmpty(emailit)|| TextUtils.isEmpty(passwordit)||TextUtils.isEmpty(confirmit)) {
                    Toast.makeText(getApplicationContext(), "enter the required fields", Toast.LENGTH_LONG).show();
                }
                else if (!(passwordit.equals(confirmit)))
                {
                    Toast.makeText(getApplicationContext(), "password and confirm password does not match", Toast.LENGTH_LONG).show();
                }
                else if(passwordit.length()<6)
                {
                    Toast.makeText(getApplicationContext(), "password must be of atleast 6 characters ", Toast.LENGTH_LONG).show();

                }
                else {

                        authiiii.createUserWithEmailAndPassword(emailit, passwordit).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "There is some Error occured while signing in ", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    if (cbt.isChecked()) {
                                        startActivity(new Intent(getApplicationContext(), Seller.class));
                                        finish();
                                    }
                                    if (cbs.isChecked()) {

                                        startActivity(new Intent(getApplicationContext(),Customer.class));
                                        finish();
                                    }
                                }
                            }
                        });
                    }


            }
        });
    }
    private void filemethod() {
        try {
            fstream=openFileOutput("userdetails22",MODE_PRIVATE);
            fstream.write(count);
            fstream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
