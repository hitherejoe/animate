package com.hitherejoe.animate.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hitherejoe.animate.R;

public class AnimationApisAdapter extends RecyclerView.Adapter<AnimationApisAdapter.ViewHolder> {
    private String[] mApiArray;
    private OnRecyclerItemClick mOnRecyclerItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mApiNameTextView;
        public ViewHolder(TextView v) {
            super(v);
            mApiNameTextView = v;
            mApiNameTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnRecyclerItemClickListener != null) {
                int itemPosition = getAdapterPosition();
                mOnRecyclerItemClickListener.onItemClick(itemPosition);
            }
        }
    }

    public AnimationApisAdapter(String[] apiArray, OnRecyclerItemClick onRecyclerItemClick) {
        mApiArray = apiArray;
        mOnRecyclerItemClickListener = onRecyclerItemClick;
    }

    @Override
    public AnimationApisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_api, parent, false);
        return new ViewHolder((TextView) v.findViewById(R.id.text_name));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mApiNameTextView.setText(mApiArray[position]);
    }

    @Override
    public int getItemCount() {
        return mApiArray.length;
    }

    public interface OnRecyclerItemClick {
        void onItemClick(int position);
    }
}