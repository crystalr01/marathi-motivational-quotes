package com.rameshwar.motivationalquotesenglish;

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

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_image_text_activity, parent, false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {

        final NoticeData currentItem = list.get(position);

        holder.newsText.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());

        try {
            if (currentItem.getImage() != null)
                Glide.with(context).load(currentItem.getImage()).into(holder.newsImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context,FullImageActivity.class);
            intent.putExtra("newsImage",currentItem.getImage());
            intent.putExtra("newsText",currentItem.getTitle());
            intent.putExtra("date",currentItem.getDate());
            intent.putExtra("time",currentItem.getTime());
            intent.putExtra("image",currentItem.getImage());
            context.startActivity(intent);


        }
    });
//
//        if (position%3 == 0) {
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        private TextView newsText, date, time;
        private ImageView newsImage;
       // TemplateView templateView;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);

            newsText = itemView.findViewById(R.id.custom_news);
            newsImage = itemView.findViewById(R.id.custom_image_news);
            date = itemView.findViewById(R.id.custom_date);
            time = itemView.findViewById(R.id.custom_time);
        //    templateView = itemView.findViewById(R.id.nativead);
        }
    }
}
