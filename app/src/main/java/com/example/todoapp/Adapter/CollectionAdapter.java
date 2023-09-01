package com.example.todoapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Interface.CollectionInterface;
import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.Collections;
import com.example.todoapp.R;
import com.example.todoapp.ViewModel.HomeViewModel;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private List<Collections> collectionsList;
    private Context context;
    private CollectionInterface collection_interface;
    HomeViewModel viewModel;

    public CollectionAdapter(List<Collections> collectionsList, Context context, CollectionInterface collection_interface, HomeViewModel viewModel) {
        this.collectionsList = collectionsList;
        this.context = context;
        this.collection_interface=collection_interface;
        this.viewModel=viewModel;
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

//        viewModel.getIsSelectionEnabled().observe(, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if(aBoolean){
//                    holder.radioButton.setVisibility(View.VISIBLE);
//                }else {
//                    holder.radioButton.setVisibility(View.GONE);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return collectionsList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,description;
        private RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.collection_name);
            description=itemView.findViewById(R.id.collection_desc);
            radioButton=itemView.findViewById(R.id.collection_selection_radio_btn);


            itemView.setOnClickListener(this);

        }

         @Override
         public void onClick(View view) {
            collection_interface.onCollectionSelected(collectionsList.get(getAdapterPosition()).getId());
         }
     }

     public void updateList(List<Collections> list){
        collectionsList.clear();
        collectionsList.addAll(list);
        notifyDataSetChanged();
     }
}
