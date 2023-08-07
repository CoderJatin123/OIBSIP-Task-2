package com.example.todoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Model.Collections;
import com.example.todoapp.R;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<Collections> collectionsList;
    private Context context;

    public CollectionAdapter(List<Collections> collectionsList, Context context) {
        this.collectionsList = collectionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.collection_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(collectionsList.get(position).getName());
        holder.description.setText(collectionsList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return collectionsList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.collection_name);
            description=itemView.findViewById(R.id.collection_desc);

        }
    }
}
