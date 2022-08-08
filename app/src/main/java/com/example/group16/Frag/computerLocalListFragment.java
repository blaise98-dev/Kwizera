package com.example.group16.Frag;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.group16.Adapters.computerAdapter;
import com.example.group16.R;
import com.example.group16.computer_model.computerModel;
import com.example.group16.db_pc.DBconfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class computerLocalListFragment extends Fragment {
    FloatingActionButton fbtn_local;
    RecyclerView userDataRecyclerView;
    ArrayList<computerModel> computerDataArrayList;
    computerAdapter computerDataAdapter;
    DBconfig dbconfig;
    TextView no_pc;
    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.local_list_fragment, container, false);
        userDataRecyclerView = view.findViewById(R.id.pcRecycleview);
        no_pc = view.findViewById(R.id.no_pc);
        computerDataArrayList = new ArrayList<>();
        dbconfig = new DBconfig(requireContext());
        dbconfig.open();
        Cursor cursor = dbconfig.getcomputerTypes();
        if (cursor.moveToFirst()) {
            if (computerDataArrayList != null) {
                computerDataArrayList.clear();
                no_pc.setVisibility(View.VISIBLE);
            }
            no_pc.setVisibility(View.GONE);
            do {
                String pc_serial = cursor.getString(cursor.getColumnIndexOrThrow("serial_number"));
                String pc_name = cursor.getString(cursor.getColumnIndexOrThrow("computer_name"));
                String pc_ram = cursor.getString(cursor.getColumnIndexOrThrow("computer_ram"));
                String pc_size = cursor.getString(cursor.getColumnIndexOrThrow("screen_size"));

                computerDataArrayList.add(new computerModel(pc_serial,pc_name,pc_ram,pc_size));

            } while (cursor.moveToNext());

            computerDataAdapter = new computerAdapter(computerDataArrayList, getContext());
            userDataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            userDataRecyclerView.setVerticalScrollBarEnabled(true);
            computerDataAdapter.notifyDataSetChanged();
            userDataRecyclerView.setAdapter(computerDataAdapter);
        }



        fbtn_local = view.findViewById(R.id.fbtn_local);
        fbtn_local.setOnClickListener(view1 -> {
            Computer_form formAdd= new Computer_form(0);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.thisView, formAdd, Computer_form.class.getSimpleName())
                    .commit();
        });

       return view;
    }
}