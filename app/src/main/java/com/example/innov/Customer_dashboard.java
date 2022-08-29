package com.example.innov;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Customer_dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    ProductsAdapter adapter;
    ArrayList<Modalclass> productList;
    FirebaseFirestore db;
    //ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
        //progressDialog=new ProgressDialog(this);
        //progressDialog.setCancelable(false);
        //progressDialog.setMessage("Fetching data...");
        //progressDialog.show();
        recyclerView = findViewById(R.id.recyclerview_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        adapter = new ProductsAdapter(Customer_dashboard.this, productList);

        recyclerView.setAdapter(adapter);


        db = FirebaseFirestore.getInstance();
        db.collection("Products").orderBy("Price",Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                     //if (progressDialog.isShowing())
                       //   progressDialog.dismiss();
                    Log.e("Firestore errror", error.getMessage());
                    return;

                }
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        productList.add(dc.getDocument().toObject(Modalclass.class));
                    }
                    adapter.notifyDataSetChanged();
                   // if (progressDialog.isShowing())
                     //   progressDialog.dismiss();
                }
            }


        });


    }
}