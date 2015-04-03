package com.quaffon.team9.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quaffon.team9.quaffonbloomington.R;

/**
 * Created by Ike on 4/2/2015.
 */
public class FoodTab extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.food_tab, container, false);
        return v;
    }
}