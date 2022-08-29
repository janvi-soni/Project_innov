package com.example.innov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Seller_dashboard extends AppCompatActivity {
    Button b,c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        c=findViewById(R.id.btn2);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent k=new Intent(Seller_dashboard.this,Login.class);
                startActivity(k);
                finish();
            }
        });


        b=findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(Seller_dashboard.this, seller_addtocart.class);
                startActivity(l);

            }
        });
    }
}