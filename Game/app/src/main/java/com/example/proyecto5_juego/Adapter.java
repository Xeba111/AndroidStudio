package com.example.proyecto5_juego;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderData> {

    ArrayList<String> historyList;

    public Adapter(ArrayList<String> historyList) {
        this.historyList = historyList;
    }


    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,
                null,
                false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.assignData(historyList.get(position));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }


    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolderData extends RecyclerView.ViewHolder{

        TextView data;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            data = (TextView) itemView.findViewById(R.id.dataHistory);
        }


        public void assignData(String s) {
            data.setText(s);
        }
    }

    public void updateData(ArrayList<String> list) {
        final HCallback diffCallback = new HCallback(this.historyList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.historyList.clear();
        this.historyList.addAll(list);
        diffResult.dispatchUpdatesTo(this);
    }
}
