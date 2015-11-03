package com.pd_opensource.ratemyisp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.pd_opensource.ratemyisp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RateISPFragment extends Fragment {

    @Bind(R.id.spinner_isp)
    Spinner ispSpinner;

    @Bind(R.id.rating_bar)
    RatingBar ratingBar;

    public RateISPFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rate_isp, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.isp_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ispSpinner.setAdapter(adapter);
        ispSpinner.setSelection(0);
    }

}
