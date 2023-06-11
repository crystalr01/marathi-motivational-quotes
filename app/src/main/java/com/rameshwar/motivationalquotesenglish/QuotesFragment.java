package com.rameshwar.motivationalquotesenglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class QuotesFragment extends Fragment {

    public QuotesFragment() {
        // Required empty public constructor
    }

    CardView allQuotes, vivekanandQuotes, kalamQuotes, businessQuotes, motiTextQuotes,
            earnMoney, ambedkar, maharaj, festival, ad;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        allQuotes = view.findViewById(R.id.all_quotes);
        vivekanandQuotes = view.findViewById(R.id.vivekanand_quotes);
        kalamQuotes = view.findViewById(R.id.kalam_quotes);
        businessQuotes = view.findViewById(R.id.bussiness_quotes);
        motiTextQuotes = view.findViewById(R.id.text_quotes);
        ambedkar = view.findViewById(R.id.ambedkar);
        maharaj = view.findViewById(R.id.maharaj);
        festival = view.findViewById(R.id.festival);
        earnMoney = view.findViewById(R.id.earn_money);
        ad = view.findViewById(R.id.speacial);


        allQuotes.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });

        vivekanandQuotes.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), VivekanandActivity.class);
            startActivity(intent);
        });

        kalamQuotes.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), KalamActivity.class);
            startActivity(intent);
        });

        businessQuotes.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), BussinessActivity.class);
            startActivity(intent);
        });

        motiTextQuotes.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), MotivationalTextActivity.class);
            startActivity(intent);
        });

        ambedkar.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), Ambedkar.class);
            startActivity(intent);
        });

        maharaj.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), Maharaj.class);
            startActivity(intent);
        });

        festival.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), Festival.class);
            startActivity(intent);
        });

        earnMoney.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), EarnMoney.class);
            startActivity(intent);
        });

        ad.setOnClickListener(v -> {
            ShowAd();
            Intent intent = new Intent(getContext(), Adver.class);
            startActivity(intent);
        });

        return view;
    }

    private void ShowAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(getContext(), "ca-app-pub-6972616661119579/2844891585", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                interstitialAd.show(getActivity());
            }
        });
    }
}
