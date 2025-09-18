# Android OOP Step By Step: ConstraintLayout

In this tutorial, we will explore the basics of ConstraintLayout in Android development. ConstraintLayout is a powerful layout manager that allows you to create complex layouts with a flat view hierarchy.

## Getting Started
1. Open your Android project in Android Studio.
2. Add a new layout file by right-clicking on the `res/layout` folder.
3. Select `New` -> `Layout Resource File` and name it `activity_main.xml`.

## Adding a ConstraintLayout
Replace the root element in your layout file with `ConstraintLayout`:
```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Your UI elements go here -->

</androidx.constraintlayout.widget.ConstraintLayout>
```