glass-gdk-progressbar
=====================

Painless ProgressBar for Glass GDK (Updated to XE21)

The XE21 just came out so here is the updated progress bar!

Here's an example:

```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mSliderView = (SliderView) findViewById(R.id.slider);
        mSliderView.startProgress(5 * 1000); // 5 seconds
        // mSliderView.startIndeterminate();
    }
```
