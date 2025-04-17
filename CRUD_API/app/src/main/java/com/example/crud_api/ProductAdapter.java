package com.example.crud_api;

import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Produit> produits;
    private RecyclerView recyclerView;  // Add RecyclerView reference
    private GestureDetector gestureDetector;

    // Pass RecyclerView reference to the constructor
    public ProductAdapter(Context context, List<Produit> produits) {
        this.context = context;
        this.produits = produits;
        this.recyclerView = recyclerView;  // Store the RecyclerView reference
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // When a double-tap is detected, get the position of the clicked item
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (view != null) {
                    int position = recyclerView.getChildAdapterPosition(view);  // Get the position of the clicked item
                    if (position != RecyclerView.NO_POSITION) {
                        Produit selectedProduit = produits.get(position);

                        // Start ProdActivity with the selected product's details
                        Intent intent = new Intent(context, ProdActivity.class);
                        intent.putExtra("id", selectedProduit.getIdProduit());
                        intent.putExtra("des", selectedProduit.getNomProduit());
                        intent.putExtra("pri", String.valueOf(selectedProduit.getPrixProduit()));
                        context.startActivity(intent);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the list
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bind the product data to the view holder
        Produit produit = produits.get(position);
        holder.nomTextView.setText("Nom: " + produit.getNomProduit());
        holder.prixTextView.setText("Prix: " + produit.getPrixProduit());
    }

    @Override
    public int getItemCount() {
        return produits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        TextView nomTextView;
        TextView prixTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(android.R.id.text1);
            prixTextView = itemView.findViewById(android.R.id.text2);
            itemView.setOnTouchListener(this); // Set touch listener to detect double-click
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // Pass the touch event to GestureDetector to handle the double-click
            return gestureDetector.onTouchEvent(event);
        }
    }
}
