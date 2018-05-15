package com.smartprix.photoviewer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static android.util.Log.i;
import static com.smartprix.photoviewer.BuildConfig.DEBUG;

public class ImageItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "ARG_COLUMN_COUNT";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ImageItemAdapter mAdapter;
    private AtomicIntegerArray list;

    public ImageItemFragment() {
    }

    @SuppressWarnings("unused")
    public static ImageItemFragment newInstance(int columnCount) {
        ImageItemFragment fragment = new ImageItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Context context;
        RecyclerView recyclerView;
        ((ApplicationClass)getContext().getApplicationContext()).initializeList();
        setupImageListMain(10); //test

        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        mAdapter = new ImageItemAdapter(getImageListMain(), mListener, getImageSize(mColumnCount)); // item list
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private int getImageSize(int mColumnCount) {
        return getScreenWidth() / mColumnCount;
    }

    private void setupImageListMain(int numberOfItems) {
        ImageItem item;
        try {
            JSONArray objects = loadJsonfromAssets().getJSONArray("images");

            for (int i = 0; i < numberOfItems; i++) {
                JSONObject jsonItem = objects.getJSONObject(i);
                item = new ImageItem(jsonItem);
                getImageListMain().add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (DEBUG) i("TAG", "ERROR IN JSON PARSE : setupImageListMain");
        }
    }

    private JSONObject loadJsonfromAssets() {
        String jsonString = null;
        JSONObject json = null;
        try {
            InputStream is = getContext().getAssets().open("list.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            jsonString = new String(buffer, "UTF-8");

            json = new JSONObject(jsonString);

        } catch (IOException ex) {
            ex.printStackTrace();
            if (DEBUG) i("TAG", "IO ERROR");
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            if (DEBUG) i("TAG", "ERROR in JSONParse");
            return null;
        }

        return json;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    public void refreshList() {
        if (DEBUG) i("TAG", "in refreshList");
        logList();
        mAdapter.notifyDataSetChanged();
    }

    private void logList() {
        for (ImageItem item : getImageListMain()) {
            if (DEBUG) i("TAG", "Item : " + item.description() + "\nLIKED : " + item.getLiked());
        }
    }

    public ArrayList<ImageItem> getImageListMain() {
        return ((ApplicationClass)getContext().getApplicationContext()).getList();
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(ArrayList<ImageItem> items, int position);
    }
}
