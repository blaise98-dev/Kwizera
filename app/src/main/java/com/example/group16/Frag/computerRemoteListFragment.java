package com.example.group16.Frag;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group16.Adapters.computerAdapter;
import com.example.group16.R;
import com.example.group16.computer_model.computerModel;
import com.example.group16.db_pc.DBconfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


public class computerRemoteListFragment extends Fragment {
    FloatingActionButton fab_remote;
    RecyclerView userDataRecyclerView;
    ArrayList<computerModel> computerDataArrayList;
    private FirebaseFirestore db;
    computerAdapter computerDataAdapter;
    TextView no_pc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.remote_list_fragment, container, false);
        fab_remote = view.findViewById(R.id.fab_remote);
        userDataRecyclerView = view.findViewById(R.id.pcRecycleview);
        no_pc = view.findViewById(R.id.no_pc);
        db = FirebaseFirestore.getInstance();
        computerDataArrayList = new ArrayList<>();

        fab_remote.setOnClickListener(view1 -> {
            Computer_form formFrag= new Computer_form(1);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.remoteLayout, formFrag, Computer_form.class.getSimpleName())
                    .commit();
        });

        db.collection("dbComputers")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        no_pc.setVisibility(View.GONE);
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            computerDataArrayList.add(new computerModel(Objects.requireNonNull(document.getData().get("serial")).toString(), Objects.requireNonNull(document.getData().get("name")).toString(), Objects.requireNonNull(document.getData().get("ram")).toString(), Objects.requireNonNull(document.getData().get("size")).toString()));
                            computerDataAdapter = new  computerAdapter(computerDataArrayList, getContext());
                            userDataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            userDataRecyclerView.setVerticalScrollBarEnabled(true);
                            userDataRecyclerView.setAdapter(computerDataAdapter);
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
        return view;
    }
}