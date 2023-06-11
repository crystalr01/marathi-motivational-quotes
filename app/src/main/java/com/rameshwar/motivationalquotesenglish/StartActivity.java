package com.rameshwar.motivationalquotesenglish;

import static com.google.firebase.encoders.json.BuildConfig.APPLICATION_ID;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

public class StartActivity extends AppCompatActivity {



    TabLayout tabLayout;
    ViewPager viewPager;

    String url = "https://play.google.com/store/apps/dev?id=5194175941837931502";

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPger);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        FirebaseMessaging.getInstance().subscribeToTopic("notification");


//    @Override
//    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.share_button,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId())
//        {
//            case  R.id.shareApp:
//                try {
//                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                    shareIntent.setType("text/plain");
//                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
//                    String shareMessage= "\nLet me recommend you this application \nFor high level motivational quotes.\nDownload and Share" +
//                            " Marathi Motivational Quotes with more than 10+ Categories!\n\n";
//                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.rameshwar.motivationalquotesenglish";
//                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
//                    startActivity(Intent.createChooser(shareIntent, "choose one"));
//                } catch(Exception e) {
//                    //e.toString();
//                }
//                return true;
//
////            case  R.id.moreApps:
////                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=5194175941837931502"));
////                startActivity(intent);
////                return true;
//
//            case  R.id.rateApp:
//                Intent intent1 = new Intent(Intent.ACTION_VIEW);
//                intent1.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.rameshwar.marathimotivationalquotes"));
//                startActivity(intent1);
//                return true;
//
////            case  R.id.moti:
////                Intent intent2 = new Intent(Intent.ACTION_VIEW);
////                intent2.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.rameshwar.shinde.shetkari"));
////                startActivity(intent2);
////                return true;
////
////            case  R.id.shayariApp:
////                Intent intent3 = new Intent(Intent.ACTION_VIEW);
////                intent3.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.rameshwar.marathi.goodmorningandgoodnightshayri"));
////                startActivity(intent3);
////                return true;
////
//            case  R.id.follow:
//                Intent intent4 = new Intent(Intent.ACTION_VIEW);
//                intent4.setData(Uri.parse("https://www.instagram.com/its_ram_patil._/"));
//                startActivity(intent4);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you want to Exit...?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StartActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })

                .setNeutralButton("More Apps", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}