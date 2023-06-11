package com.rameshwar.motivationalquotesenglish;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.bumptech.glide.Glide;
import com.downloader.PRDownloader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class Full_Image_Activity extends AppCompatActivity implements MaxAdListener {

    private String url;

   // private MaxAdView adView;
    private MaxInterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        ImageView fullImage = findViewById(R.id.fullImage);
        Button downloadImg = findViewById(R.id.download);
        Button shareimg = findViewById(R.id.share);

        url = getIntent().getStringExtra("image");

//        adView = findViewById(R.id.maxAdView);
//
//      //   Load the ad
//        adView.loadAd();

        MobileAds.initialize(this);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        interstitialAd = new MaxInterstitialAd("6b878bae21fc0585", this);
        interstitialAd.setListener( this );

        // Load the first ad
        interstitialAd.loadAd();

        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                // AppLovin SDK is initialized, start loading ads
            }
        });

                shareimg.setOnClickListener(v -> {

                    shareimg.setText("Share Again!");
                    shareimg.setEnabled(true);

                    BitmapDrawable drawable = (BitmapDrawable)fullImage.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();

                    String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"title",null);
                    Uri uri = Uri.parse(bitmapPath);

                    try {

                        Intent imgIntent = new Intent(Intent.ACTION_SEND);
                        imgIntent.setType("image/png");
                        imgIntent.putExtra(Intent.EXTRA_STREAM,uri);
                        imgIntent.putExtra(Intent.EXTRA_TEXT,"Download the App and get Daily Motivational Quotes to motivate you! Share or Download the quotes an share to your friends to motivate them." +
                                " In this app you can Access more than 1000 of quotes images like motivation,Business,Dr.A.P.J.Abdul kalam quotes,Swami Vivekanand" +
                                " Quotes And many more that you want.You can also Copy or Shre the Text Motivational Lines. \n\n Download From Google Play" +
                                "  \n\n https://play.google.com/store/apps/details?id=com.rameshwar.marathimotivationalquotes");
                        imgIntent = Intent.createChooser(imgIntent,"Share by");
                        startActivity(imgIntent);

                        Toast.makeText(this, "Sharing", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                Glide.with(this).load(url).into(fullImage);

                PRDownloader.initialize(getApplicationContext());

                downloadImg.setOnClickListener(v -> {
                    ShowAd();
                    downloadImg.setText("Save Again!");
                    downloadImg.setEnabled(true);

                    Toast.makeText(this, "Saved to Gallery", Toast.LENGTH_SHORT).show();


                    checkPermission();
                });


                getSupportActionBar().setTitle("Download");

            }

            private void checkPermission() {

                Dexter.withContext(this)
                        .withPermissions(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            downloadImg();
                        } else {
                            Toast.makeText(Full_Image_Activity.this, "Please allow all permissions", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();

            }


            private void downloadImg() {

      /*  ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Downloading...");
        pd.setCancelable(false);
        pd.show();

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        PRDownloader.download(url, file.getPath(), URLUtil.guessFileName(url, null, null))
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {
                        pd.dismiss();

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {

                        long per = progress.currentBytes * 100 / progress.totalBytes;

                        pd.setMessage("Downloading... " + per + "%");

                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        pd.dismiss();
                        Toast.makeText(Full_Image_Activity.this, "Downloading Completed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Error error) {
                    }
                });

       */

        ImageView fullImage = findViewById(R.id.fullImage);
        BitmapDrawable drawable = (BitmapDrawable)fullImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"title",null);
        Uri uri = Uri.parse(bitmapPath);

        try {

            Intent imgIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            imgIntent.setType("image/png");
            imgIntent.putExtra(Intent.EXTRA_STREAM,uri);
            //   imgIntent.putExtra(Intent.EXTRA_TEXT,"Download the App and get access of your all needs to share on any social media platforms \n\n Download From Google Play" +
            //        "  \n\n https://play.google.com/store/apps/details?id=com.rameshwar.marathi.goodmorningandgoodnightshayri")
            //    imgIntent = Intent.createChooser(imgIntent,"Share by");
            startActivity(imgIntent);

            //    Toast.makeText(this, "Sharing", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

    }

    private void ShowAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this, "ca-app-pub-6972616661119579/2844891585", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                interstitialAd.show(Full_Image_Activity.this);
            }
        });
    }
}