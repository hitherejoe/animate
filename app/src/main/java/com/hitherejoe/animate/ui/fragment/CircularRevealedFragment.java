package com.hitherejoe.animate.ui.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitherejoe.animate.R;


public class CircularRevealedFragment extends Fragment {

    public CircularRevealedFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_circular_revealed, container, false);
    }

}
