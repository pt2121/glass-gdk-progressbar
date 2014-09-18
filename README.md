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
        mSliderView.startProgress(5 * 1000, animatorListener); // 5 seconds with listener
        // mSliderView.startProgress(5 * 1000); // 5 seconds without listener
        // mSliderView.startIndeterminate();
    }
```


**Changing the color in xml**


![gif 02](https://raw.githubusercontent.com/prt2121/glass-gdk-progressbar/master/screenshot/video2.gif)

```
    <com.prt2121.glass.widget.SliderView
        android:id="@+id/slider_top"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true"
        app:progress_color="@android:color/holo_blue_bright" />

    <com.prt2121.glass.widget.SliderView
        android:id="@+id/slider_bottom"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:progress_color="@android:color/holo_blue_bright" />
```

screen record

![gif 02](https://github.com/prt2121/glass-gdk-progressbar/blob/master/screenshot/video.gif)

```
    <com.prt2121.glass.widget.SliderView
        android:id="@+id/slider_top"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentTop="true" />

    <com.prt2121.glass.widget.SliderView
        android:id="@+id/slider_bottom"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true" />
```
