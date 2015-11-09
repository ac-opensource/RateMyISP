package com.pd_opensource.ratemyisp.fragments;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.pd_opensource.ratemyisp.R;
import com.pd_opensource.ratemyisp.models.Events;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import pocketknife.BindArgument;
import pocketknife.PocketKnife;
import pocketknife.SaveState;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRatingFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ISP = "ARG_ISP";

    @BindArgument(ARG_ISP)
    @SaveState
    public String mISP;

    @Bind(R.id.rating_bar)
    RatingBar ratingBar;

    @Bind(R.id.add_rating_comment)
    EditText comments;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;


    public AddRatingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRatingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRatingFragment newInstance(String isp) {
        AddRatingFragment fragment = new AddRatingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ISP, isp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGoogleApiClient();
        PocketKnife.bindArguments(this);
        PocketKnife.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_rating, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PocketKnife.saveInstanceState(this, outState);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @OnClick(R.id.btn_submit_rating)
    public void onSubmit(Button button) {
        ParseObject reviewObject = new ParseObject("Reviews");
        reviewObject.put("isp", mISP);
        reviewObject.put("comment", comments.getText().toString());
        if(mLastLocation != null) {
            ParseGeoPoint currentLocation = new ParseGeoPoint(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            reviewObject.put("location", currentLocation);
        } else {
            if(mGoogleApiClient.isConnected()) {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);

                if(mLastLocation != null) {
                    ParseGeoPoint currentLocation = new ParseGeoPoint(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                    reviewObject.put("location", currentLocation);
                }
            }
        }
        reviewObject.put("rate", ratingBar.getRating());
        reviewObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    EventBus.getDefault().post(new Events(Events.EVENT_REVIEW_SUBMITTED, ""));
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        mGoogleApiClient.disconnect();
        super.onDestroy();
    }
}
