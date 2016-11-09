package com.gaurav.accolitetest.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.gaurav.accolitetest.R;
import com.gaurav.accolitetest.model.LocationModel;
import com.gaurav.accolitetest.model.StateListModel;
import com.gaurav.accolitetest.utils.NetworkRequest;
import com.google.android.gms.maps.model.LatLng;

import java.io.EOFException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private TextInputLayout tilSearchLayout;
    private boolean userInitiated, showError;
    private RecyclerView recyclerView;
    private TextView tvErrorMessage;
    private Handler mHandler;
    private String searchString = "";
    private TextInputEditText tietSearch;
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            if (searchString.isEmpty()) {
                return;
            }
            Context context = MainActivity.this;
            if (TextUtils.isDigitsOnly(searchString.replace(".", ""))) {
                if (Patterns.IP_ADDRESS.matcher(searchString).matches()) {
                    final ProgressDialog progressDialog = ProgressDialog.show(context, "",
                            getString(R.string.please_wait), true);
                    closeKeyBoard();
                    Call<LocationModel> call = NetworkRequest.searchLocation(searchString);
                    call.enqueue(new Callback<LocationModel>() {
                        @Override
                        public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                            LocationModel model = response.body();
                            if (model == null) {
                                showError(R.string.error_invalid_ip_address);
                            } else {
                                String snippet = model.getCity() + ", " + model.getState() + ", " + model.getCountry();
                                startActivity(new Intent(MainActivity.this, MapsActivity.class)
                                        .putExtra("location", new LatLng(model.getLatitude(), model.getLongitude()))
                                        .putExtra("title", model.getNetwork())
                                        .putExtra("snippet", snippet));
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<LocationModel> call, Throwable t) {
                            if (t instanceof EOFException) {
                                showError(R.string.error_invalid_ip_address);
                            }
                            progressDialog.dismiss();
                        }
                    });
                } else {
                    showError = true;
                    tilSearchLayout.setError(getString(R.string.error_invalid_ip_address));
                }
            } else {
                final ProgressDialog progressDialog = ProgressDialog.show(context, "",
                        getString(R.string.please_wait), true);
                closeKeyBoard();
                Call<StateListModel> call = NetworkRequest.searchForState(searchString);
                call.enqueue(new Callback<StateListModel>() {
                    @Override
                    public void onResponse(Call<StateListModel> call, Response<StateListModel> response) {
                        StateListModel model = response.body();
                        if (model.getResult().size() == 0) {

                        } else {

                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<StateListModel> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
            if (userInitiated) {
                userInitiated = false;
            }
        }
    };

    private void showError(int errorId) {
        tvErrorMessage.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        tvErrorMessage.setText(errorId);
    }

    private void closeKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tietSearch.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        tilSearchLayout = (TextInputLayout) findViewById(R.id.search_layout);
        tietSearch = (TextInputEditText) findViewById(R.id.search);
        tietSearch.addTextChangedListener(this);
        tietSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                EditText editComment = (EditText) view;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >=
                            (editComment.getRight() - editComment.getCompoundDrawables()[2].getBounds().width())) {
                        userInitiated = true;
                        mHandler.removeCallbacks(mRunnable);
                        mRunnable.run();
                        return true;
                    }
                }
                return false;
            }
        });
        tvErrorMessage = (TextView) findViewById(R.id.error_message);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (showError) {
            tilSearchLayout.setError(null);
            showError = false;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        mHandler.removeCallbacks(mRunnable);
        if (editable.length() > 2) {
            searchString = editable.toString();
            mHandler.postDelayed(mRunnable, 800);
        }
    }
}
