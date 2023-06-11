package com.rameshwar.motivationalquotesenglish;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<com.rameshwar.motivationalquotesenglish.Data> shayariData;
    Context context;
    Activity activity = this.activity;


    public Adapter(ArrayList<com.rameshwar.motivationalquotesenglish.Data> sharariData, Context context) {
        this.shayariData = sharariData;
        this.context = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_shayari_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.shayariText.setText(shayariData.get(position).getShayari());
        setAnimation(holder.itemView);

        Random random = new Random();
        int color = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
        holder.cardview.setCardBackgroundColor(color);

        holder.cardview.setOnClickListener(v -> {
            Random random1 = new Random();
            int color1 = Color.argb(255, random1.nextInt(256), random1.nextInt(256), random1.nextInt(256));
            holder.cardview.setCardBackgroundColor(color1);

        });

        holder.copyBtn.setOnClickListener(v -> {

            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", shayariData.get(position).getShayari().concat("\n\nDownload App - shorturl.at/nsDI8"));
            clipboard.setPrimaryClip(clip);

            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();

        });

        holder.shareBtn.setOnClickListener(v -> {

            try {

                Intent txtIntent = new Intent(Intent.ACTION_SEND);
                txtIntent.setType("text/plain");
                //    txtIntent.putExtra(Intent.EXTRA_SUBJECT, "Shayari");
                txtIntent.putExtra(Intent.EXTRA_TEXT,shayariData.get(position).getShayari().concat("\n\nDownload App - shorturl.at/nsDI8"));
                txtIntent = Intent.createChooser(txtIntent,"Shre by");
                holder.itemView.getContext().startActivity(txtIntent);

                Toast.makeText(context, "Sharing", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        holder.whatsapp.setOnClickListener(view -> {

            try {
                Intent whatsappShare = new Intent(Intent.ACTION_SEND);
                whatsappShare.setType("text/plain");
                whatsappShare.setPackage("org.whatsapp");
                whatsappShare.setPackage("com.whatsapp");
                whatsappShare.putExtra(Intent.EXTRA_TEXT,shayariData.get(position).getShayari().concat("\n\nDownload App - shorturl.at/nsDI8"));
                context.startActivity(whatsappShare);

            } catch (Exception exception) {
                Toast.makeText(context, "WhatsApp Not Installed..!", Toast.LENGTH_SHORT).show();
            }
        });

//        if (position%5 == 0) {
//
//            MobileAds.initialize(context);
//            AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-6972616661119579/2616635148")
//                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                        @Override
//                        public void onNativeAdLoaded(NativeAd nativeAd) {
//                            NativeTemplateStyle styles = new
//                                    NativeTemplateStyle.Builder().build();
//                            TemplateView templateView = holder.templateView;
//                            templateView.setStyles(styles);
//                            templateView.setNativeAd(nativeAd);
//                        }
//                    })
//                    .build();
//
//            adLoader.loadAd(new AdRequest.Builder().build());
//
//            holder.templateView.setVisibility(View.VISIBLE);
//        }
    }

    private void setAnimation(View view){
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return shayariData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView shayariText;
        ImageView copyBtn;
        ImageView shareBtn;
        ImageView whatsapp;
        CardView cardview;
     //   TemplateView templateView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shayariText = itemView.findViewById(R.id.shayariText);
            copyBtn = itemView.findViewById(R.id.copyButton);
            shareBtn = itemView.findViewById(R.id.shareButton);
            cardview = itemView.findViewById(R.id.item_card);
        //    templateView = itemView.findViewById(R.id.nativead);
            whatsapp = itemView.findViewById(R.id.whatsapp);
        }
    }
}