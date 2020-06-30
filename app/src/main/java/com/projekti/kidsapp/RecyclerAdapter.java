package com.projekti.kidsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //item view type
    private static final int TYPE = 1;
    //Activity's context
    private final Context context;
    //The list
    private final List<Object> listRecyclerItem;

    //Constructor that takes two parameters
    public RecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    //Provides a reference to each view in the item view
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView type;
        private TextView animals;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            animals = itemView.findViewById(R.id.animals);
        }
    }

    //Creates a new view for item view and this method is invoked by the layout manager
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case TYPE:

            default:
                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_item, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    //Replaces the content in the views and this method is invoked by the layout manager
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {
            case TYPE:
            default:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                Animals animals1 = (Animals) listRecyclerItem.get(position);

                //Add the item details to the item view
                itemViewHolder.type.setText(animals1.gettype());
                itemViewHolder.animals.setText(animals1.getanimals());
        }

    }

    //Size of the list from AnimalsActivity
    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
