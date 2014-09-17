package com.prt2121.glass.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * Created by prt2121 on 9/9/14.
 */
public class SliderView extends FrameLayout {

    private static final long HIDE_SLIDER_TIMEOUT_MILLIS = 2000L;
    private static final int MIN_SLIDER_WIDTH_PX = 40;
    private static final long SLIDER_BAR_RESIZE_ANIMATION_DURATION_MILLIS = 300L;
    private float animatedCount;
    private int count;
    private ObjectAnimator countAnimator;
    private final Runnable hideSliderRunnable;
    private final IndeterminateProgressView indeterminateSlider;
    private float index;
    private ViewPropertyAnimator progressAnimator;
    private float slideableScale;
    private final View slider;
    private boolean sliderShowing;
    private boolean sliderWasShowing;
    private int mProgressColor, mBackgroundColor;

    public SliderView(Context context) {
        this(context, null);
    }

    public SliderView(Context context, AttributeSet attributeset) {
        this(context, attributeset, 0);
    }

    public SliderView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ProgressBar,
                0, 0);

        try {
            mProgressColor = a.getColor(R.styleable.ProgressBar_progress_color, Color.WHITE);
            mBackgroundColor = a.getColor(R.styleable.ProgressBar_background_color, Color.TRANSPARENT);
        } finally {
            a.recycle();
        }

        count = 0;
        animatedCount = 0.0F;
        index = 0.0F;
        slideableScale = 1.0F;
        hideSliderRunnable = new Runnable() {
            public void run() {
                hideSlider(true);
            }
        };
        sliderWasShowing = false;
        sliderShowing = true;
        setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.slider_bar_height)));
        LayoutInflater.from(getContext()).inflate(R.layout.slider, this);
        slider = findViewById(R.id.slider_control);
        // TODO
        slider.setBackgroundColor(mProgressColor);
        //slider.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_bright));
        indeterminateSlider = (IndeterminateProgressView) findViewById(R.id.indeterminate_slider);
        // TODO
        indeterminateSlider.setProgressColor(mProgressColor);
        //indeterminateSlider.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_bright));
        hideSlider(false);
        hideIndeterminateSlider(false);
    }

    private void animateCountTo(float f) {
        if (countAnimator != null && countAnimator.isRunning()) {
            countAnimator.cancel();
        }
        float af[] = new float[2];
        af[0] = animatedCount;
        af[1] = f;
        countAnimator = ObjectAnimator.ofFloat(this, "animatedCount", af);
        countAnimator.setDuration(300L);
        countAnimator.start();
    }

    private int getBaseSliderWidth() {
        return Math.max((int) ((float) getResources().getDisplayMetrics().widthPixels / animatedCount), 40);
    }

    private void hideIndeterminateSlider(boolean flag) {
        int i = getResources().getDimensionPixelSize(R.dimen.slider_bar_height);
        if (flag) {
            indeterminateSlider.animate().translationY(i).setDuration(getResources().getInteger(R.integer.slider_in_out_animation_duration_ms)).setListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    indeterminateSlider.setVisibility(View.GONE);
                }
            });
            return;
        } else {
            indeterminateSlider.setTranslationY(i);
            indeterminateSlider.setVisibility(View.GONE);
            return;
        }
    }

    private void hideSlider(boolean flag) {
        if (!sliderShowing) {
            return;
        }
        int i = getResources().getDimensionPixelSize(R.dimen.slider_bar_height);
        slider.animate().cancel();
        if (flag) {
            slider.animate().translationY(i).setDuration(getResources().getInteger(R.integer.slider_in_out_animation_duration_ms));
        } else {
            slider.setTranslationY(i);
        }
        sliderShowing = false;
    }

    private void hideSliderAfterTimeout() {
        removeCallbacks(hideSliderRunnable);
        postDelayed(hideSliderRunnable, 2000L);
    }

    private void setProportionalIndex(float f, int i, boolean flag) {
        if (count < 2) {
            hideSlider(true);
        } else {
            index = f;
            float f1 = 1.0F / slideableScale;
            float f2 = ((0.5F + index) - f1 / 2.0F) * (float) (getResources().getDisplayMetrics().widthPixels / count);
            if (i != 0) {
                slider.animate().translationX(f2).setDuration(i).setInterpolator(new AccelerateDecelerateInterpolator());
            } else {
                slider.setTranslationX(f2);
            }
            if (flag) {
                showSlider(true);
                hideSliderAfterTimeout();
                return;
            }
        }
    }

    private void showIndeterminateSlider(boolean flag) {
        if (flag) {
            indeterminateSlider.animate().translationY(0.0F).setDuration(getResources().getInteger(R.integer.slider_in_out_animation_duration_ms)).setListener(new SimpleAnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    indeterminateSlider.setVisibility(View.VISIBLE);
                }
            });
            return;
        } else {
            indeterminateSlider.setTranslationY(0.0F);
            indeterminateSlider.setVisibility(View.VISIBLE);
            return;
        }
    }

    private void showSlider(boolean flag) {
        removeCallbacks(hideSliderRunnable);
        if (sliderShowing) {
            return;
        }
        slider.animate().cancel();
        if (flag) {
            slider.animate().translationY(0.0F).setDuration(getResources().getInteger(R.integer.slider_in_out_animation_duration_ms));
        } else {
            slider.setTranslationY(0.0F);
        }
        sliderShowing = true;
    }

    private void updateSliderWidth(boolean flag) {
        if (count < 2) {
            hideSlider(true);
            return;
        }
        android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) slider.getLayoutParams();
        params.width = (int) ((1.0F / slideableScale) * (float) getBaseSliderWidth());
        params.leftMargin = 0;
        slider.setLayoutParams(params);
        if (flag) {
            showSlider(true);
        }
        setProportionalIndex(index, 0, flag);
    }

    public void dismissManualProgress() {
        hideSlider(true);
    }

    float getAnimatedCount() {
        return animatedCount;
    }

    public boolean isSliderShowing() {
        return sliderShowing;
    }

    void setAnimatedCount(float f) {
        setAnimatedCount(f, true);
    }

    void setAnimatedCount(float f, boolean flag) {
        animatedCount = f;
        updateSliderWidth(flag);
    }

    public void setCount(int i) {
        setCount(i, true);
    }

    public void setCount(int i, boolean flag) {
        hideIndeterminateSlider(true);
        hideSlider(true);
        count = i;
        index = Math.max(Math.min(index, i - 1), 0.0F);
        if (flag) {
            animateCountTo(i);
            return;
        } else {
            setAnimatedCount(i, false);
            return;
        }
    }

    public void setManualProgress(float f) {
        setManualProgress(f, false);
    }

    /**
     *
     * @param percent
     * @param animate
     */
    public void setManualProgress(float percent, boolean animate) {
        hideIndeterminateSlider(true);
        showSlider(false);
        int i = getResources().getDisplayMetrics().widthPixels;
        android.widget.FrameLayout.LayoutParams layoutParams = (android.widget.FrameLayout.LayoutParams) slider.getLayoutParams();
        layoutParams.width = i;
        layoutParams.setMargins(-i, 0, 0, 0);
        slider.setLayoutParams(layoutParams);
        if (animate) {
            slider.animate().translationX(percent * (float) i);
            return;
        } else {
            slider.setTranslationX(percent * (float) i);
            return;
        }
    }

    public void setProportionalIndex(float f) {
        setProportionalIndex(f, 0, true);
    }

    public void setProportionalIndex(float f, int i) {
        setProportionalIndex(f, i, true);
    }

    public void setScale(float f) {
        slideableScale = f;
        updateSliderWidth(true);
    }

    public void startIndeterminate() {
        int i = getResources().getDisplayMetrics().widthPixels;
        android.widget.FrameLayout.LayoutParams layoutParams = (android.widget.FrameLayout.LayoutParams) slider.getLayoutParams();
        layoutParams.width = i;
        layoutParams.setMargins(0, 0, 0, 0);
        slider.setLayoutParams(layoutParams);
        if (sliderShowing) {
            sliderWasShowing = true;
            hideSlider(true);
        }
        showIndeterminateSlider(true);
        indeterminateSlider.start();
    }

    public void startProgress(long milliseconds) {
        startProgress(milliseconds, new AccelerateDecelerateInterpolator());
    }

    public void startProgress(long milliseconds, android.animation.Animator.AnimatorListener animatorListener) {
        startProgress(milliseconds, new AccelerateDecelerateInterpolator(), animatorListener);
    }

    public void startProgress(long milliseconds, TimeInterpolator timeInterpolator) {
        startProgress(milliseconds, timeInterpolator, null);
    }

    public void startProgress(long milliseconds, TimeInterpolator timeinterpolator, android.animation.Animator.AnimatorListener animatorListener) {
        hideIndeterminateSlider(true);
        slider.setTranslationX(0.0F);
        showSlider(false);
        int i = getResources().getDisplayMetrics().widthPixels;
        android.widget.FrameLayout.LayoutParams layoutParams = (android.widget.FrameLayout.LayoutParams) slider.getLayoutParams();
        layoutParams.width = i;
        layoutParams.setMargins(-i, 0, 0, 0);
        slider.setLayoutParams(layoutParams);
        progressAnimator = slider.animate().translationX(i).setDuration(milliseconds).setInterpolator(timeinterpolator).setListener(animatorListener);
        progressAnimator.start();
    }

    public void stopIndeterminate() {
        if (sliderWasShowing) {
            showSlider(true);
        }
        indeterminateSlider.stop();
        hideIndeterminateSlider(true);
    }

    public void stopProgress() {
        if (progressAnimator != null) {
            progressAnimator.cancel();
        }
        hideSlider(true);
    }

}