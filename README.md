# CircleProgressBar

![](https://user-images.githubusercontent.com/18132015/79176466-8008ad80-7e2a-11ea-8fbd-e9b2a6d497ca.jpg)

How to

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

    gradle
    maven
    sbt
    leiningen

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.nguyenkhiem7789:CircleProgressBar:0.1.0'
	}

Usage

...

    <com.nguyen.circleprogressview.CircleProgressView
        android:id="@+id/circleProgressBar"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:layout_centerInParent="true"
        app:pro_text_size="50dp"
        app:pro_stroke_width="4dp"
        app:pro_primary_color="@color/colorAccent"
        app:pro_text_color="@color/colorPrimaryDark"
        />
...
