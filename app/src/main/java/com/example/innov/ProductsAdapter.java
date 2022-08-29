package com.example.innov;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

     Context context;
    ArrayList<Modalclass> productList;

    public ProductsAdapter(Context context, ArrayList<Modalclass> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v= LayoutInflater.from(context).inflate(R.layout.layout_product, parent, false);
                return new ProductViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Modalclass product = productList.get(position);

        holder.textViewName.setText(product.Name);
        holder.textViewPrice.setText(product.Price);
        holder.textViewCategory.setText(product.Category);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,  textViewPrice,textViewCategory;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_name);
            textViewPrice = itemView.findViewById(R.id.textview_Price);
            textViewCategory=itemView.findViewById(R.id.textview_Category);


        }
    }
}