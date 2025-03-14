package com.example.myrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends  RecyclerView.Adapter<ListAdapter.MBH>{
    Context c;
    String[] titre,stitre;
    Integer[] image;

    public ListAdapter(Context c, String[] titre, String[] stitre, Integer[] image) {
        this.c = c;
        this.titre = titre;
        this.stitre = stitre;
        this.image = image;
    }

    @NonNull
    @Override
    public MBH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlayout,parent,false);
ListAdapter.MBH mbh= new ListAdapter.MBH(v);
        return mbh;
    }

    @Override
    public void onBindViewHolder(@NonNull MBH holder, int i) {
        holder.tit.setText(titre[i]);
        holder.stit.setText(stitre[i]);
        holder.img.setImageResource(image[i]);
    }

    @Override
    public int getItemCount() {
        return titre.length;
    }

    public class MBH extends RecyclerView.ViewHolder {
        TextView tit,stit;
        ImageView img;
        public MBH(@NonNull View itemView) {
            super(itemView);
            tit =itemView.findViewById(R.id.textView);
            stit=itemView.findViewById(R.id.textView);
            img=itemView.findViewById(R.id.imageView);
        }
    }

}
