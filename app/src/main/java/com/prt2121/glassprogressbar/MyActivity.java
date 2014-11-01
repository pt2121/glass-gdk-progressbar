package com.prt2121.glassprogressbar;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.prt2121.glass.widget.SliderView;


public class MyActivity extends Activity {

    private static final String TAG = MyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ((SliderView) findViewById(R.id.slider_bottom)).startProgress(4 * 1000, animatorListener);
        ((SliderView) findViewById(R.id.slider_top)).startIndeterminate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
            Log.v(TAG, "#onAnimationStart");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.v(TAG, "#onAnimationEnd");
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            Log.v(TAG, "#onAnimationCancel");
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            Log.v(TAG, "#onAnimationRepeat");
        }
    };
}
