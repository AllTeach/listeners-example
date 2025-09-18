package com.example.listenersdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {

    private Button myButton;
    private Switch mySwitch;
    private SeekBar textSizeSeekBar;
    private TextView adjustableText;
    private Button anonymousButton;
    private ImageView opacityImage;
    private SeekBar opacitySeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        myButton = findViewById(R.id.my_button);
        mySwitch = findViewById(R.id.my_switch);
        adjustableText = findViewById(R.id.adjustable_text);
        textSizeSeekBar = findViewById(R.id.text_size_seekbar);
        anonymousButton = findViewById(R.id.anonymous_button);
        opacityImage = findViewById(R.id.opacity_image);
        opacitySeekBar = findViewById(R.id.opacity_seekbar);

        // Set up listeners
        myButton.setOnClickListener(this);
        mySwitch.setOnCheckedChangeListener(this);
        textSizeSeekBar.setMax(100);
        textSizeSeekBar.setOnSeekBarChangeListener(this);
        opacitySeekBar.setOnSeekBarChangeListener(this);

        // Anonymous listener for the anonymous button
        anonymousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, getString(R.string.clicked_with_anonymous_class), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.my_button) {
            Toast.makeText(this, getString(R.string.button_clicked), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.my_switch) {
            String status = isChecked ? getString(R.string.switch_on) : getString(R.string.switch_off);
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.text_size_seekbar) {
            // Set text size with minimum of 10sp
            adjustableText.setTextSize(Math.max(10, progress));
        } else if (seekBar.getId() == R.id.opacity_seekbar) {
            // Set image opacity (alpha) - progress is 0-100, alpha needs 0.0-1.0
            float alpha = progress / 100.0f;
            opacityImage.setAlpha(alpha);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.text_size_seekbar) {
            Toast.makeText(this, getString(R.string.started_changing_text_size), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.text_size_seekbar) {
            Toast.makeText(this, getString(R.string.stopped_changing_text_size), Toast.LENGTH_SHORT).show();
        }
    }
}