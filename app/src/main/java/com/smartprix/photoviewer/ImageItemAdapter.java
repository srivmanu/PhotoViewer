package com.smartprix.photoviewer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.smartprix.photoviewer.ImageItemFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageViewViewHolder> {

    private final ArrayList<ImageItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final int mImageWidth;

    public ImageItemAdapter(ArrayList<ImageItem> items, OnListFragmentInteractionListener listener, int imageWidth) {
        this.mValues = items;
        this.mListener = listener;
        this.mImageWidth = imageWidth;
    }


    @Override
    public ImageViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ImageViewViewHolder(view, mImageWidth);
    }

    @Override
    public void onBindViewHolder(final ImageViewViewHolder holder, final int position) {
        holder.setItem(position);
        holder.setupImageView();
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(mValues, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ImageViewViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        public int mItemPosition;
        ImageView image;
        ShineButton likeButton;

        ImageViewViewHolder(View view, int mImageWidth) {
            super(view);
            this.mView = view;
            this.image = view.findViewById(R.id.imageView);
            this.likeButton = view.findViewById(R.id.like_button);
            this.likeButton.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(View view, boolean checked) {
                    mValues.get(mItemPosition).setLiked(checked);
                    if (checked) {
                        view.setAlpha(1);
                    } else {
                        view.setAlpha((float) 0.8);
                    }
                }
            });
            ViewGroup.LayoutParams params = this.image.getLayoutParams();
            params.width = mImageWidth;
            params.height = mImageWidth;
            this.image.setLayoutParams(params);
            params = this.likeButton.getLayoutParams();
            params.height = mImageWidth / 5;
            params.width = mImageWidth / 5;
            this.likeButton.setLayoutParams(params);
        }

        public void setItem(int mItemPos) {
            this.mItemPosition = mItemPos;
            mValues.get(mItemPosition).setWidthToShow(image.getLayoutParams().width);
            mValues.get(mItemPosition).setHeightToShow(image.getLayoutParams().height);
            this.likeButton.setChecked(mValues.get(mItemPosition).getLiked(), false);
        }

        void setupImageView() {
            Picasso.get()
                    .load(mValues.get(mItemPosition).getUrl())
                    .resize(mImageWidth, mImageWidth)
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .into(image);
        }

        @Override
        public String toString() {
            return "\nURL : " + mValues.get(mItemPosition).getUrl() +
                    "\nWIDTH META : " + mValues.get(mItemPosition).getWidthMeta() +
                    "\nHEIGHT META : " + mValues.get(mItemPosition).getHeightMeta() +
                    "\nWIDTH TO SHOW : " + mValues.get(mItemPosition).getWidthToShow() +
                    "\nWIDTH TO SHOW : " + mValues.get(mItemPosition).getHeightToShow();
        }

    }
}
