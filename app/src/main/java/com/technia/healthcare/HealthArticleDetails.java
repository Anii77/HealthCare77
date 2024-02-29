package com.technia.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HealthArticleDetails extends AppCompatActivity {
    TextView tv1;
    ImageView img;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout file
        setContentView(R.layout.activity_health_article_details);

        btnBack = findViewById(R.id.buttonHADBack);
        tv1 = findViewById(R.id.textViewHAD_title);
        img = findViewById(R.id.imageViewHAD);

        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleDetails.this, HealthArticle.class));
            }
        });
    }
}
