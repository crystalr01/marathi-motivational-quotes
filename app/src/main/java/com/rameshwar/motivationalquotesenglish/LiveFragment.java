package com.rameshwar.motivationalquotesenglish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LiveFragment extends Fragment {

    public LiveFragment() {
        // Required empty public constructor
    }

    ArrayList<Data> shayariData = new ArrayList<>();
    private ProgressBar progressBar;

    ImageView uploadLive;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live, container, false);

      //  uploadLive = view.findViewById(R.id.uploadLive);

//        uploadLive.setOnClickListener(view1 -> {
//            Intent intent = new Intent(getContext(), UploadLiveActivity.class);
//            startActivity(intent);
//        });


        MobileAds.initialize(getContext());

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        progressBar = view.findViewById(R.id.progressBar);

        FirebaseDatabase mData = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mData.getReference().child("live");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    progressBar.setVisibility(View.GONE);

                    for (DataSnapshot snap : snapshot.getChildren()

                    ) {

                        Data data = new Data(snap.getValue().toString());
                        shayariData.add(data);

                        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        Adapter adapter = new Adapter(shayariData, getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        layoutManager.setStackFromEnd(true);
                        layoutManager.setReverseLayout(true);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);

            }
        });

        return view;
    }
}