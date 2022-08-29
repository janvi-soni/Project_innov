package com.example.innov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Seller extends AppCompatActivity {
    EditText name,businessname,category,city,email,phone;
    MaterialButton gobtn;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        db = FirebaseFirestore.getInstance();
        name=findViewById(R.id.name);
        businessname=findViewById(R.id.businessname);
        category=findViewById(R.id.category);
        city=findViewById(R.id.city);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);


        gobtn = findViewById(R.id.btnRegister);
        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Business_Name = businessname.getText().toString();
                String Category = category.getText().toString();
                String City = city.getText().toString();
                String Email = email.getText().toString();
                String Phone = phone.getText().toString();
                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Business_Name) || TextUtils.isEmpty(Category)||
                        TextUtils.isEmpty(City)|| TextUtils.isEmpty(Email)|| TextUtils.isEmpty(Phone)) {
                    Toast.makeText(getApplicationContext(), "Please enter the required fields", Toast.LENGTH_LONG).show();
                } else {


                    Map<String, Object> user = new HashMap<>();
                    user.put("Name", Name);
                    user.put("Business_Name", Business_Name);
                    user.put("Category", Category);
                    user.put("City", City);
                    user.put("Email", Email);
                    user.put("Phone", Phone);


                    db.collection("seller")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent l = new Intent(Seller.this, Seller_dashboard.class);
                                    startActivity(l);
                                    finish();
                                    Toast.makeText(Seller.this, "Successfully added data to firestore", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                    Toast.makeText(Seller.this, "Failed", Toast.LENGTH_SHORT).show();


                                }
                            });

                }
            }
        });

    }
}