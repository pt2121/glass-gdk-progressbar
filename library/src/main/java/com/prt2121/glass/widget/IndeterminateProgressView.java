package com.prt2121.glass.widget;

/**
 * Created by prt2121 on 9/9/14.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public class IndeterminateProgressView extends View {

    private static final int GAP_SIZE_PIXELS = 8;
    private static final int MINIMUM_FRAME_DELAY_MS = 16;
    private static final int NUM_GAPS = 3;
    private static final float SPEED_PIXELS_MS = 0.4F;
    private static final Paint WHITE_PAINT;
    final int gaps[];
    private final float height;
    private boolean running;

    public IndeterminateProgressView(Context context) {
        this(context, null);
    }

    public IndeterminateProgressView(Context context, AttributeSet attributeset) {
        this(context, attributeset, 0);
    }

    public IndeterminateProgressView(Context context, AttributeSet attributeset, int i) {
        super(context, attributeset, i);
        //WHITE_PAINT.setColor(context.getResources().getColor(android.R.color.holo_blue_bright));
        gaps = new int[6];
        setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        height = context.getResources().getDimension(R.dimen.slider_bar_height);
    }

    private void connect(Canvas canvas, int i, int j) {
        if (j > i) {
            drawRect(canvas, i, j);
            return;
        } else {
            drawRect(canvas, i, getWidth());
            drawRect(canvas, 0, j);
            return;
        }
    }

    private void drawRect(Canvas canvas, int i, int j) {
        canvas.drawRect(i, 0.0F, j, height, WHITE_PAINT);
    }

    private void drawRects(Canvas canvas, int ai[]) {
        int i = 0;
        while (i < 3) {
            int j = 1 + i * 2;
            int k;
            if (2 + i * 2 > -1 + ai.length) {
                k = 0;
            } else {
                k = 2 + i * 2;
            }
            connect(canvas, ai[j], ai[k]);
            i++;
        }
    }

    int getProgress() {
        return (int) (0.4F * (float) SystemClock.uptimeMillis());
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = getWidth();
        int j = getProgress();
        int k = i / 3;
        for (int l = 0; l < 3; l++) {
            int i1 = (j + l * k) % (i + 8);
            int j1 = (i1 * i1) / (i + 8);
            gaps[l * 2] = j1 - 8;
            gaps[1 + l * 2] = j1;
        }

        drawRects(canvas, gaps);
        if (running) {
            postInvalidateDelayed(16L);
        }
    }

    public void start() {
        running = true;
        postInvalidate();
    }

    public void stop() {
        running = false;
    }

    static {
        WHITE_PAINT = new Paint();
        WHITE_PAINT.setColor(-1);
    }
}