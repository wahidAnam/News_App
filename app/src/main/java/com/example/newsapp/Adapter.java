package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Article> arrayList = new ArrayList<>();

    public Adapter(Context context, ArrayList<Article> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Article article = arrayList.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,webview.class);
                intent.putExtra("url",article.getUrl());
                context.startActivity(intent);
            }
        });

        String url = arrayList.get(position).getUrlToImage();
        holder.time.setText("Published At:-"+arrayList.get(position).getPublishedAt());
        holder.author.setText(arrayList.get(position).getAuthor());
        holder.mainheading.setText(arrayList.get(position).getTitle());
        holder.content.setText(arrayList.get(position).getContent());
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mainheading,content,author,time,category;
        CardView cardView;
        ImageView imageview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainheading = itemView.findViewById(R.id.mainheading);
            content = itemView.findViewById(R.id.content);
            author = itemView.findViewById(R.id.author);
            time = itemView.findViewById(R.id.time);
            cardView = itemView.findViewById(R.id.cardView);
            imageview = itemView.findViewById(R.id.imageview);
        }
    }
}
