package com.example.app4;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;

public class DetailsActivity extends AppCompatActivity {

    ImageView imgPreview;
    TextView tvName, tvPath, tvSize;
    Button btnDelete;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imgPreview = findViewById(R.id.imgPreview);
        tvName = findViewById(R.id.tvName);
        tvPath = findViewById(R.id.tvPath);
        tvSize = findViewById(R.id.tvSize);
        btnDelete = findViewById(R.id.btnDelete);

        imageUri = Uri.parse(getIntent().getStringExtra("imageUri"));

        imgPreview.setImageURI(imageUri);

        File file = new File(imageUri.getPath());

        tvName.setText("Name: " + file.getName());
        tvPath.setText("Path: " + file.getPath());
        tvSize.setText("Size: " + file.length() + " bytes");

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (d, i) -> {
                        file.delete();
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}