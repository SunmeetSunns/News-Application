package com.example.kyachallahai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    String title,content,imageUrl,url,description;
    private TextView Title,SubHeading,Content;
    private ImageView Images;
    private Button ReadNewsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");
        imageUrl=getIntent().getStringExtra("image");
        content=getIntent().getStringExtra("content");
        url=getIntent().getStringExtra("url");
        Title=findViewById(R.id.TextVNews);
        SubHeading=findViewById(R.id.SubNews);
        Content=findViewById(R.id.cntnP);
        Images=findViewById(R.id.ImageViewNews);
        ReadNewsBtn=findViewById(R.id.Button);
        Title.setText(title);
        SubHeading.setText(description);
        Content.setText(content);
        Picasso.get().load(imageUrl).into(Images);
        ReadNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });




    }
}