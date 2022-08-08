package com.example.group16.Adapters;

import com.example.group16.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.group16.computer_model.computerModel;
import com.example.group16.viewHolder.computerViewHolder;

import java.util.ArrayList;

public class computerAdapter extends RecyclerView.Adapter<computerViewHolder>
{
    ArrayList<computerModel> computerDataArrayList;
    computerViewHolder computerDataViewHolder;
    Context context;

    public computerAdapter(ArrayList<computerModel> computerDataArrayList, Context context) {
        this.computerDataArrayList = computerDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public computerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.computerlist, parent, false);
        return new computerViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull computerViewHolder holder, int position) {
        final computerModel pcData = computerDataArrayList.get(position);
        holder.BindcomputerData(pcData);
        computerDataViewHolder = holder;
    }

    @Override
    public int getItemCount() {
        return computerDataArrayList.size();
    }
        @Override
    public long getItemId(int position) {
        return position;
    }
        @Override
    public int getItemViewType(int position) {
        return position;
    }

}
