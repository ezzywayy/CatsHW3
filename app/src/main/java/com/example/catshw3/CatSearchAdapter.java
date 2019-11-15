package com.example.catshw3;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatSearchAdapter extends RecyclerView.Adapter<CatSearchAdapter.CatViewHolder> {
    public ArrayList<Cat> catArrayList;

    public CatSearchAdapter(ArrayList<Cat> catArrayList){
        this.catArrayList = catArrayList;
    }

    public void setData(ArrayList<Cat> catList) {
        this.catArrayList = catList;
    }
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat,parent,false);
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Cat cat = catArrayList.get(position);
        final Context context = holder.view.getContext();
        holder.catName.setText(cat.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, CatDetailActivity.class);
                intent1.putExtra("id", cat.getId());
                context.startActivity(intent1);
            }

        });


    }

    @Override
    public int getItemCount() {
        return catArrayList.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public TextView catName;
        public View view;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            catName = itemView.findViewById(R.id.textView);
        }
    }
    public void filterList(ArrayList<Cat> filteredList) {
        this.catArrayList = filteredList;
        notifyDataSetChanged();
    }
//

}
