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

// Status Fragment

public class TrendingFragment extends Fragment {

    public TrendingFragment() {
        // Required empty public constructor
    }

    ArrayList<Data> shayariData = new ArrayList<>();
    private ProgressBar progressBar;

    ImageView uploadStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trending, container, false);

        MobileAds.initialize(getContext());

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    //    uploadStatus = view.findViewById(R.id.uploadStatus);

//        uploadStatus.setOnClickListener(view1 -> {
//            Intent intent = new Intent(getContext(), UploadStatusActivity.class);
//            startActivity(intent);
//        });


//         <ImageView
//        android:id="@+id/uploadStatus"
//        android:layout_width="54dp"
//        android:layout_height="54dp"
//        android:background="@drawable/back"
//        android:padding="9dp"
//        android:layout_margin="15dp"
//        android:src="@drawable/ic_baseline_add_circle_24"
//        app:layout_constraintBottom_toBottomOf="parent"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintTop_toBottomOf="@+id/progressBar"
//        app:tint="@color/white" />


                progressBar = view.findViewById(R.id.progressBar);

        FirebaseDatabase mData = FirebaseDatabase.getInstance();
        DatabaseReference mRef = mData.getReference().child("Status");

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
                        layoutManager.setStackFromEnd(true);
                        layoutManager.setReverseLayout(true);
                        recyclerView.setLayoutManager(layoutManager);
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