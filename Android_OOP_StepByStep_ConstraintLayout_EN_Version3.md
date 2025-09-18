# Android OOP Step-by-Step: Listeners and Interfaces (with Visual Layout, Documentation Links & Run Results)

## Introduction

In this tutorial, you’ll learn how to handle user events in Android using OOP principles.  
You’ll also learn to add UI elements **using the visual designer (drag-and-drop) in ConstraintLayout** or by editing XML.  
**Each step shows you how to find the correct listener interface on the Android Developer website and what to expect when you run the app!**

---

## Step 1: Add a Button (with ConstraintLayout)

### 1.1 Add a Button Using the Design Tab

- Open `res/layout/activity_main.xml`.
- Switch to the **Design** or **Split** tab at the bottom of the editor.
- In the **Palette** (left side), find **Button**.
- **Drag** the Button onto the phone screen preview.
- In the **Attributes** panel (right side), set:
    - **id**: `my_button`
    - **text**: `Click Me`
- **Drag the round circles** (constraints) to connect the button to the top and sides of the screen.

### 1.2 How to Find the Listener Interface (Android Developer Site)

- Go to the [Android Button documentation](https://developer.android.com/reference/android/widget/Button).
- The Android Developer website is the **official resource for all Android UI components**. It lists all properties, methods, and related interfaces for each view.
- **To find which interface you need for handling events**, scroll down to the "Public methods" section and look for methods like `setOnClickListener(View.OnClickListener l)`.  
  Click on `View.OnClickListener` to learn about that interface and its required method (`onClick(View v)`).

### 1.3 Implement the Interface

In `MainActivity.java`:

```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.my_button);
        myButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.my_button) {
            Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
```

### 1.4 **Run the app: What should you see?**

- The app launches with a single button labeled **“Click Me”** at the top.
- When you tap the button, a small popup (Toast) appears at the bottom saying:  
  `Button Clicked!`

---

## Step 2: Add a Switch (with ConstraintLayout)

### 2.1 Drag a Switch

- In the **Design** tab, drag a **Switch** from the Palette under “Buttons”.
- Place it below the Button.
- Set its **id** to `my_switch` and **text** to `Toggle Switch`.
- Connect it with constraints to the button above and the sides of the screen.

### 2.2 How to Find the Listener Interface (Android Developer Site)

- Visit the [Android Switch documentation](https://developer.android.com/reference/android/widget/Switch).
- The site shows you that `Switch` inherits from `CompoundButton`, and in the "Public methods" section you'll see `setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener)`.
- Click on `CompoundButton.OnCheckedChangeListener` to learn which method you must implement (usually `onCheckedChanged`).

### 2.3 Implement the Interface

```java
public class MainActivity extends AppCompatActivity implements
    View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...previous code...
        mySwitch = findViewById(R.id.my_switch);
        mySwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.my_switch) {
            String status = isChecked ? "Switch is ON" : "Switch is OFF";
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        }
    }
}
```

### 2.4 **Run the app: What should you see?**

- The app now has a button and a switch underneath it.
- Tapping the button still shows `Button Clicked!`
- Toggling the switch ON shows a Toast:  
  `Switch is ON`
- Toggling it OFF shows:  
  `Switch is OFF`

---

## Step 3: Add a SeekBar and TextView (with ConstraintLayout)

### 3.1 Drag and Place

- Drag a **TextView** below the Switch. Set its **id** to `adjustable_text` and text to `Adjust my size!`.
- Drag a **SeekBar** below the TextView. Set its **id** to `text_size_seekbar`.
- Connect the SeekBar’s top to the TextView and the TextView’s top to the Switch.

### 3.2 How to Find the Listener Interface (Android Developer Site)

- Visit the [Android SeekBar documentation](https://developer.android.com/reference/android/widget/SeekBar).
- On the site, look for the method `setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener l)`.
- Click on `SeekBar.OnSeekBarChangeListener` to see that you need to implement three methods:
    - `onProgressChanged`
    - `onStartTrackingTouch`
    - `onStopTrackingTouch`

### 3.3 Implement the Interface (with Toasts for Touch Events)

```java
public class MainActivity extends AppCompatActivity implements
    View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar textSizeSeekBar;
    private TextView adjustableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...previous code...
        adjustableText = findViewById(R.id.adjustable_text);
        textSizeSeekBar = findViewById(R.id.text_size_seekbar);
        textSizeSeekBar.setMax(100);
        textSizeSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.text_size_seekbar) {
            adjustableText.setTextSize(Math.max(10, progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.text_size_seekbar) {
            Toast.makeText(this, "Started changing text size", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.text_size_seekbar) {
            Toast.makeText(this, "Stopped changing text size", Toast.LENGTH_SHORT).show();
        }
    }
}
```

### 3.4 **Run the app: What should you see?**

- The app now has: button, switch, text, and a SeekBar.
- Drag the SeekBar left/right and the text size of “Adjust my size!” should grow/shrink.
- When you start dragging the SeekBar, a Toast appears:  
  `Started changing text size`
- When you stop dragging, you’ll see:  
  `Stopped changing text size`

---

## Step 4: Add a Button with Anonymous Class

### 4.1 Drag Another Button

- Drag a **Button** below the SeekBar.
- Set its **id** to `anonymous_button` and text to `Anonymous Click`.

### 4.2 Set Listener with Anonymous Class

In `onCreate`:

```java
Button anonymousButton = findViewById(R.id.anonymous_button);
anonymousButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Clicked with anonymous class!", Toast.LENGTH_SHORT).show();
    }
});
```

### 4.3 **Run the app: What should you see?**

- The new button labeled “Anonymous Click” is now below the SeekBar.
- Tapping it shows a Toast:  
  `Clicked with anonymous class!`

---

## Tips for Using the Android Developer Website

- The [Android Developer website](https://developer.android.com/reference/android/widget) is the **definitive source for all Android UI components and their listeners**.
- For any UI element:
    1. Search for the component (e.g., “android Button”).
    2. Scroll to find “Public methods” and look for `setOn...Listener` methods.
    3. Click the interface name to see what methods you need to implement.
- This resource helps you **find any event interface and understand what code you need** for handling user actions.

---

## Challenge

- Drag an **ImageView** and a **SeekBar** to the layout.
- Use the Android Developer site to find the listener needed to change the ImageView’s opacity when using the SeekBar.
- **Run the app:** When you drag the SeekBar, the image should get more or less transparent!

---

**Now you know how to use the visual designer, official documentation, and OOP listeners to build interactive Android apps!**