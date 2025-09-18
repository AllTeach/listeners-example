# שלב אחרי שלב באנדרואיד: מאזינים וממשקים (עם עיצוב חזותי, קישורים לאתר התיעוד ומה רואים בהרצה)

## פתיחה

במדריך הזה תלמדו כיצד לעבוד עם אירועים באנדרואיד באמצעות עקרונות OOP (תכנות מונחה עצמים).  
תלמדו להוסיף רכיבי ממשק **באמצעות עורך חזותי (Drag & Drop) עם ConstraintLayout** או על ידי עריכת XML.  
**בכל שלב תמצאו איך לחפש את הממשק המתאים באתר המפתחים של אנדרואיד ומה אמורים לראות בהרצה!**

---

## שלב 1: הוספת כפתור (Button) עם ConstraintLayout

### 1.1 הוספת כפתור באמצעות העיצוב החזותי

- פתחו `res/layout/activity_main.xml`.
- עברו ללשונית **Design** או **Split** בתחתית העורך.
- ב-**Palette** (שמאל), מצאו **Button**.
- **גררו** את הכפתור למסך התצוגה.
- בחלונית **Attributes** (ימין), הגדירו:
    - **id**: `my_button`
    - **text**: `Click Me`
- **חברו את העיגולים** (קונסטריינטים) לצדדים ולחלקו העליון של המסך.

### 1.2 איך מוצאים את הממשק באתר המפתחים של אנדרואיד

- היכנסו ל[תיעוד Button באתר המפתחים](https://developer.android.com/reference/android/widget/Button).
- האתר הוא המקור הרשמי לכל רכיבי האנדרואיד, כולל מאפיינים, פונקציות וממשקים.
- חפשו ב-"Public methods" את הפונקציה `setOnClickListener(View.OnClickListener l)`.  
  לחצו על `View.OnClickListener` כדי לראות אילו מתודות יש לממש (`onClick(View v)`).

### 1.3 מימוש הממשק

ב-`MainActivity.java`:

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

### 1.4 **הריצו את האפליקציה: מה אמורים לראות?**

- מופיע כפתור בודד למעלה עם הכיתוב **"Click Me"**.
- בלחיצה עליו מופיע Toast עם:  
  `Button Clicked!`

---

## שלב 2: הוספת מתג (Switch) עם ConstraintLayout

### 2.1 גרירת מתג

- ב-**Design**, גררו **Switch** מה-Palette ל"Buttons".
- מקמו אותו מתחת לכפתור.
- הגדירו **id** ל-`my_switch` ו-**text** ל-`Toggle Switch`.
- חברו לקונסטריינטים של הכפתור ושל המסך.

### 2.2 איך מוצאים את הממשק באתר המפתחים של אנדרואיד

- כנסו ל[תיעוד Switch באתר המפתחים](https://developer.android.com/reference/android/widget/Switch).
- תראו שה-Switch יורש מ-CompoundButton, וב-"Public methods" יש `setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener)`.
- לחצו על `CompoundButton.OnCheckedChangeListener` כדי לראות את המתודה הדרושה (`onCheckedChanged`).

### 2.3 מימוש הממשק

```java
public class MainActivity extends AppCompatActivity implements
    View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Switch mySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...קוד קודם...
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

### 2.4 **הריצו: מה אמורים לראות?**

- יש כפתור ומתג מתחתיו.
- לחיצה על הכפתור עדיין מציגה `Button Clicked!`
- העברת המתג ל-ON מציגה Toast:  
  `Switch is ON`
- העברה ל-OFF מציגה:  
  `Switch is OFF`

---

## שלב 3: SeekBar ו-TextView (ConstraintLayout)

### 3.1 גרירה ומיקום

- גררו **TextView** מתחת למתג. הגדירו **id** ל-`adjustable_text` וטקסט ל-`Adjust my size!`.
- גררו **SeekBar** מתחת לטקסט. הגדירו **id** ל-`text_size_seekbar`.
- קשרו את SeekBar ל-TextView ולמתג בעזרת constraints.

### 3.2 איך מוצאים את הממשק באתר המפתחים של אנדרואיד

- עברו ל[תיעוד SeekBar באתר המפתחים](https://developer.android.com/reference/android/widget/SeekBar).
- חפשו את המתודה `setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener l)`.
- לחצו על `SeekBar.OnSeekBarChangeListener` – יש לממש שלוש מתודות:
    - `onProgressChanged`
    - `onStartTrackingTouch`
    - `onStopTrackingTouch`

### 3.3 מימוש הממשק (כולל Toast לתחילת/סיום גרירה)

```java
public class MainActivity extends AppCompatActivity implements
    View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private SeekBar textSizeSeekBar;
    private TextView adjustableText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...קוד קודם...
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
            Toast.makeText(this, "התחלת לשנות את גודל הטקסט", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.text_size_seekbar) {
            Toast.makeText(this, "סיימת לשנות את גודל הטקסט", Toast.LENGTH_SHORT).show();
        }
    }
}
```

### 3.4 **הריצו: מה אמורים לראות?**

- מופיעים: כפתור, מתג, טקסט, ו-SeekBar.
- גרירת ה-SeekBar משנה את גודל הטקסט!
- בתחילת הגרירה מופיע Toast:  
  `התחלת לשנות את גודל הטקסט`
- בסיום:  
  `סיימת לשנות את גודל הטקסט`

---

## שלב 4: כפתור עם מחלקה אנונימית

### 4.1 גררו כפתור נוסף

- גררו **Button** מתחת ל-SeekBar.
- הגדירו **id** ל-`anonymous_button` וטקסט ל-`Anonymous Click`.

### 4.2 מחלקה אנונימית ב-onCreate

```java
Button anonymousButton = findViewById(R.id.anonymous_button);
anonymousButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Clicked with anonymous class!", Toast.LENGTH_SHORT).show();
    }
});
```

### 4.3 **הריצו: מה אמורים לראות?**

- יש כפתור חדש עם הטקסט “Anonymous Click”.
- בלחיצה עליו מופיע Toast:  
  `Clicked with anonymous class!`

---

## טיפים לאתר המפתחים של אנדרואיד

- [אתר התיעוד של אנדרואיד](https://developer.android.com/reference/android/widget) הוא המקור לכל רכיב ולכל מאזין.
- לכל רכיב:
    1. חפשו את הרכיב (למשל “android Button”).
    2. חפשו ב-"Public methods" מתודות `setOn...Listener`.
    3. לחצו על שם הממשק כדי לדעת אילו מתודות צריך לממש.
- האתר עוזר להבין **בדיוק איזה ממשק/קוד צריך** לכל אירוע.

---

## אתגר

- גררו **ImageView** ו-**SeekBar**.
- מצאו באתר המפתחים את המאזין המתאים כדי לשנות את השקיפות של התמונה עם SeekBar.
- **הריצו:** גרירת ה-SeekBar תשנה את השקיפות של התמונה!

---

**עכשיו אתם יודעים להשתמש בעורך החזותי, בתיעוד הרשמי ובמאזינים OOP כדי לבנות אפליקציות אינטראקטיביות באנדרואיד!**