package com.rameshwar.motivationalquotesenglish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadShayari extends AppCompatActivity {

    DatabaseReference reference;

    EditText title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_shayari);

        getSupportActionBar().setTitle("Upload Shayari Data");

        title = findViewById(R.id.newsTitle);
        Button upload = findViewById(R.id.uploadNewsBtn);

        reference = FirebaseDatabase.getInstance().getReference("Shayari");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }

    private void insertData() {

        String shayari = title.getText().toString();

        ShayriData data = new ShayriData(shayari);

        reference.push().setValue(shayari);

        Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

    }
}