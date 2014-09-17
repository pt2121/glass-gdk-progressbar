Glass GDK Progressbar
=====================

Painless ProgressBar for Glass GDK!

###Including In Your Project

#####Gradle
```
compile 'com.github.prt2121:ProgressBar:1.0@aar'
```

###Usage

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mSliderView = (SliderView) findViewById(R.id.slider);
        mSliderView.startProgress(5 * 1000); // 5 seconds
        // mSliderView.startIndeterminate();
    }
```

