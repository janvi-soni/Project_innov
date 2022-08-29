package com.example.innov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Customer extends AppCompatActivity {
    EditText name,city,zipcode, age,gender,email;
    MaterialButton gobtn,btn2;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        db = FirebaseFirestore.getInstance();
        name=findViewById(R.id.name);
        city=findViewById(R.id.city);
        zipcode=findViewById(R.id.zipcode);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        email=findViewById(R.id.email);
        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent k=new Intent(Customer.this,Login.class);
                startActivity(k);
                finish();
            }
        });

        gobtn = findViewById(R.id.btnRegister);
        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String City = city.getText().toString();
                String Zipcode = zipcode.getText().toString();
                String Age = age.getText().toString();
                String Gender = gender.getText().toString();
                String Email = email.getText().toString();
                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(City) || TextUtils.isEmpty(Zipcode) ||
                        TextUtils.isEmpty(Age) || TextUtils.isEmpty(Gender) || TextUtils.isEmpty(Email)) {
                    Toast.makeText(getApplicationContext(), "enter the required fields", Toast.LENGTH_LONG).show();
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Name", Name);
                    user.put("City", City);
                    user.put("Zipcode", Zipcode);
                    user.put("Age", Age);
                    user.put("Gender", Gender);
                    user.put("Email", Email);

                    db.collection("customer")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent l = new Intent(Customer.this, Customer_dashboard.class);
                                    startActivity(l);

                                    Toast.makeText(Customer.this, "Successfully added data to firestore", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                    Toast.makeText(Customer.this, "Failed", Toast.LENGTH_SHORT).show();


                                }
                            });

                }
            }
        });

    }
}