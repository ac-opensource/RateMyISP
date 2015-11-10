package com.pd_opensource.ratemyisp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.pd_opensource.ratemyisp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import pocketknife.BindArgument;
import pocketknife.PocketKnife;
import pocketknife.SaveState;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewRatingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewRatingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ISP = "ARG_ISP";

    @BindArgument(ARG_ISP)
    @SaveState
    public String mISP;

    @Bind(R.id.lv_review_list)
    ListView reviewList;


    public ViewRatingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewRatingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewRatingsFragment newInstance(String isp) {
        ViewRatingsFragment fragment = new ViewRatingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ISP, isp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PocketKnife.bindArguments(this);
        PocketKnife.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_ratings, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<>(getActivity(), "Reviews");
        adapter.setTextKey("comment");
        reviewList.setAdapter(adapter);
    }
}
