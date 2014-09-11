package com.prt2121.glassprogressbar;

import android.app.Activity;
import android.os.Bundle;

import com.prt2121.glass.widget.SliderView;


public class IndeterminateActivity extends Activity {

    private SliderView mSliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mSliderView = (SliderView) findViewById(R.id.slider);
        mSliderView.startIndeterminate();
    }

}
