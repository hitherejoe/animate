package com.hitherejoe.animate.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hitherejoe.animate.R;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private String[] mGridItemArray;
    private OnRecyclerItemClick mOnRecyclerItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnRecyclerItemClickListener != null) {
                mOnRecyclerItemClickListener.onItemClick(mTextView);
            }
        }
    }

    public GridAdapter(String[] gridItemArray, OnRecyclerItemClick onRecyclerItemClick) {
        mGridItemArray = gridItemArray;
        mOnRecyclerItemClickListener = onRecyclerItemClick;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid, parent, false);
        return new ViewHolder((TextView) v.findViewById(R.id.text_name));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mGridItemArray[position]);
    }

    @Override
    public int getItemCount() {
        return mGridItemArray.length;
    }

    public interface OnRecyclerItemClick {
        void onItemClick(View View);
    }
}