package com.example.group16.Frag;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.group16.R;
import com.example.group16.computer_model.computerModel;
import com.example.group16.db_pc.DBconfig;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Computer_form extends Fragment {
    private TextInputEditText serial_number;
    private TextInputEditText computer_name;
    private TextInputEditText computer_ram;
    private TextInputEditText screen_size;
    private Button save_data;
    private FirebaseFirestore db;
    private DBconfig configdb;
    int flag;

    public Computer_form(int flag) {
        // Required empty public constructor
        this.flag=flag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.computer_form_fragment, container, false);

        serial_number=view.findViewById(R.id.serialNumber);
        computer_name=view.findViewById(R.id.name);
        computer_ram=view.findViewById(R.id.ram);
        screen_size=view.findViewById(R.id.size);
        save_data=view.findViewById(R.id.btnClick);
        db = FirebaseFirestore.getInstance();
        configdb = new DBconfig(requireContext());
        configdb.open();
        save_data.setOnClickListener(view1 -> {
            if (Objects.requireNonNull( serial_number.getText()).toString().isEmpty()) {
                serial_number.setError("Serial number is required");
                return;
            }
            if (Objects.requireNonNull( computer_name.getText()).toString().isEmpty()) {
                computer_name.setError("computer name is required");
                return;
            }
            if (Objects.requireNonNull( computer_ram.getText()).toString().isEmpty()) {
                computer_ram.setError("computer ram is required");
                return;
            }
            if (Objects.requireNonNull( screen_size.getText()).toString().isEmpty()) {
                screen_size.setError("Screen size is required");
                return;
            }
            if (flag == 1) {
                saveRemote(serial_number.getText().toString(), computer_name.getText().toString(),computer_ram.getText().toString(),screen_size.getText().toString(), view);
                return;
            }
            long result = configdb.insert(serial_number.getText().toString(), computer_name.getText().toString(), computer_ram.getText().toString(),screen_size.getText().toString());
            if (result > 0) {
                Snackbar snackbar = Snackbar.make(view, "New Computer information Added!!!", Snackbar.LENGTH_LONG);
                snackbar.show();

                final Handler handler = new Handler();
                handler.postDelayed(() -> {
                    // Do something after 2s = 2000ms
                    requireActivity().getSupportFragmentManager().popBackStack();
                }, 2000);
            }
            else {
                Snackbar snackbar = Snackbar.make(view, "Whoop! Something went wrong", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.RED);
                snackbar.show();
            }
        });

//        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rbtngrp);
//
//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
//
//            switch(checkedId) {
//                case R.id.bit32:
//                    // switch to fragment 1
//                    Toast.makeText(getActivity(),"32 bit select",Toast.LENGTH_SHORT).show();
//
//                    break;
//                case R.id.bit64:
//                    Toast.makeText(getActivity(),"64 bit select",Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        });


        return view;
    }

    private void saveRemote(String pc_serial, String pc_name, String pc_ram, String pc_size, View view) {

        // creating a collection reference
        // for our Firebase Firestore database.
        CollectionReference dbcomputers = db.collection("dbComputers");
        // adding our data to our courses object class.
        computerModel pc = new computerModel(pc_serial, pc_name,pc_ram,pc_size);

        // below method is use to add data to Firebase Firestore.
        dbcomputers.add(pc).addOnSuccessListener(documentReference -> {
            Snackbar snackbar = Snackbar.make(view, "Wow! data saved remotely successfully", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(Color.BLUE);
            snackbar.show();
        }).addOnFailureListener(e -> {
            Snackbar snackbar = Snackbar.make(view, "something went wrong,data not saved", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(Color.RED);
            snackbar.show();
        });

    }
}