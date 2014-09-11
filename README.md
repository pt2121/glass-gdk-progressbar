glass-gdk-progressbar
=====================

Work In Progress

Painless ProgressBar for Glass GDK (Updated to XE21)

The XE21 just came out so here is the updated progress bar!

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

###Including In Your Project

#####Maven

```
<dependency>
  <groupId>com.github.prt2121</groupId>
  <artifactId>ProgressBar</artifactId>
  <version>0.9</version>
  <type>aar</type>
</dependency>
```

#####Gradle
```
compile 'com.github.prt2121:ProgressBar:0.9'
```
