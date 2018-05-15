package com.smartprix.photoviewer;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity {
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        position = getIntent().getIntExtra("position", -1);
        ((TextView) findViewById(R.id.textDetailView)).setText(((ApplicationClass) getApplicationContext()).getList().get(position).description());
        loadImage();
        getSupportActionBar().setTitle(((ApplicationClass) getApplicationContext()).getList().get(position).getTitle());
        fixFab();
    }

    private void fixFab() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (((ApplicationClass) getApplicationContext()).getList().get(position).getLiked()) {
            fab.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white, null)));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean like =
                        ((ApplicationClass) getApplicationContext()).getList().get(position).getLiked() != null ? !((ApplicationClass) getApplicationContext()).getList().get(position).getLiked() : true;
                ((ApplicationClass) getApplicationContext()).getList().get(position).setLiked(like);
                if (((ApplicationClass) getApplicationContext()).getList().get(position).getLiked()) {
                    fab.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white, null)));
                } else {
                    fab.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.black, null)));
                }
            }
        });
    }

    private void loadImage() {
        Picasso.get().load(((ApplicationClass) getApplicationContext()).getList().get(position).getUrl())
                .into((ImageView) findViewById(R.id.imageView));
    }
}
