package com.example.catshw3;

import android.content.Context;
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
        final Cat catObject = catArrayList.get(position);
        final Context context = holder.view.getContext();
        holder.catName.setText(catObject.getName());
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
//    public void filterList(ArrayList<Cat> filteredList) {
//        catArrayList = filteredList;
//        notifyDataSetChanged();
//    }
//    CatSearchAdapter(List<Cat> catList) {
//        this.catList = catList;
//        catListFull = new ArrayList<>(catList);
//    }
//    @Override
//    public Filter getFilter() {
//        return catFilter;
//    }
//    private Filter catFilter = new Filter() {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Cat> filteredCatList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                filteredCatList.addAll(catListFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (Cat cat : catListFull) {
//                    if (cat.getName().toLowerCase().contains(filterPattern)) {
//                        filteredCatList.add(cat);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredCatList;
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            catList.clear();
//            catList.addAll((List)results.values);
//            notifyDataSetChanged();
//        }
//    };

}
