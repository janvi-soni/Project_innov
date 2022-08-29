package com.example.innov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class seller_addtocart extends AppCompatActivity  {
    EditText a,b,c;
    Button btn;
    Spinner s;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_addtocart);
        a=findViewById(R.id.product_Name);
        b=findViewById(R.id.productPrice);
        c=findViewById(R.id.productCategory);
        btn=findViewById(R.id.btn);
    db = FirebaseFirestore.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = a.getText().toString();
                String Price = b.getText().toString();
                String Category = c.getText().toString();
                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Price) || TextUtils.isEmpty(Category)
                ) {
                    Toast.makeText(getApplicationContext(), "Please enter the required fields", Toast.LENGTH_LONG).show();
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Name", Name);
                    user.put("Price", Price);
                    user.put("Category", Category);
                    db.collection("Products")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Intent l = new Intent(seller_addtocart.this, Customer_dashboard.class);
                                    startActivity(l);

                                    Toast.makeText(seller_addtocart.this, "Successfully added data to firestore", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {

                                    Toast.makeText(seller_addtocart.this, "Failed", Toast.LENGTH_SHORT).show();


                                }
                            });

                }
            }
        });



    }



}
