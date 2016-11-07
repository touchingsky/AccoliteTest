package com.gaurav.accolitetest.activity;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.gaurav.accolitetest.R;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private TextInputEditText tietSearch;
    private Handler mHandler;
    private String searchString = "";
    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            if (TextUtils.isDigitsOnly(searchString.replace(".", ""))) {
                // TODO Make ip search
            } else {
             //TODO make state search
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        tietSearch = (TextInputEditText) findViewById(R.id.search);
        tietSearch.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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
