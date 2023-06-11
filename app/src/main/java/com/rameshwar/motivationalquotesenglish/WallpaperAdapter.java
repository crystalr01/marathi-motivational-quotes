package com.rameshwar.motivationalquotesenglish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    private ArrayList<String> list;
    private Context context;


    public WallpaperAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull

    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_image_activity, parent,false);

        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(list.get(position)).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Full_Image_Activity.class);
                intent.putExtra("image",list.get(position));
                context.startActivity(intent);
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
//

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WallpaperViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        //TemplateView templateView;


        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_image);
      //      templateView = itemView.findViewById(R.id.nativead);

        }
    }

}