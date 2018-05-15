package com.smartprix.photoviewer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.smartprix.photoviewer.BuildConfig.DEBUG;

public class MainActivity extends AppCompatActivity implements ImageItemFragment.OnListFragmentInteractionListener {


    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFragments();
    }

    private void setupFragments() {
        currentFragment = ImageItemFragment.newInstance(2);
        getFragmentManager().beginTransaction().add(R.id.fragment, currentFragment).commit();
    }

    @Override
    public void onListFragmentInteraction(ArrayList<ImageItem> items, int position) {
        if (DEBUG) Log.i("TAG", "ITEM DETAILS : \n" + items.get(position).description());
        Intent intent = new Intent();
        intent.setClass(this, ScrollingActivity.class);
        intent.putExtra("list",items);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((ImageItemFragment) currentFragment).refreshList();
    }

    public void setLiked(int position,boolean liked) {
        ((ImageItemFragment)currentFragment).getImageListMain().get(position).setLiked(liked);
    }
}
