package com.pd_opensource.ratemyisp.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.pd_opensource.ratemyisp.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RateISPFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.rate_header)
    TextView header;

    public RateISPFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rate_isp, container, false);
        ButterKnife.bind(this, view);
        initializeViews(view);

        return view;
    }

    private void initializeViews(View view) {
        String headerText = getString(R.string.rate_header);
        Spannable headerSpan = new SpannableString(headerText);
        int indexOfHeader = headerText.indexOf(getString(R.string.header));
        headerSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#FF304F")), indexOfHeader, headerText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        headerSpan.setSpan(new StyleSpan(Typeface.BOLD), indexOfHeader, headerText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        headerSpan.setSpan(new ForegroundColorSpan(Color.WHITE), 0, indexOfHeader, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        header.setText(headerSpan);

        setOnClickListener((ViewGroup) view);
    }

    public void setOnClickListener(ViewGroup viewGroup) {
        View v;
        for(int i = 0; i < viewGroup.getChildCount(); i++) {
            v = viewGroup.getChildAt(i);
            if(v instanceof ImageButton) v.setOnClickListener(this);
            if(v instanceof ViewGroup) setOnClickListener((ViewGroup) v);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
