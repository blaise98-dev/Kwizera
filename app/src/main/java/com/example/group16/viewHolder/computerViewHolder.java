package com.example.group16.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.group16.R;
import com.example.group16.computer_model.computerModel;

public class computerViewHolder extends RecyclerView.ViewHolder{
    TextView t1,t2,t3,t4,t5;
    Context context;
    private computerModel pcData;

    public computerViewHolder(@NonNull View itemView,Context context) {
        super(itemView);
        this.context = context;
        t1 = itemView.findViewById(R.id.textView1);
        t2 = itemView.findViewById(R.id.textView2);
        t3 = itemView.findViewById(R.id.textView3);
        t4 = itemView.findViewById(R.id.textView4);
        t5 = itemView.findViewById(R.id.textView5);

    }

    public void BindcomputerData(final computerModel pcData) {
        this.pcData = pcData;
        t1.setText(pcData.getName());
        t2.setText(pcData.getSerial());
        t3.setText(pcData.getSize());
        t5.setText(pcData.getRam());
    }
}
