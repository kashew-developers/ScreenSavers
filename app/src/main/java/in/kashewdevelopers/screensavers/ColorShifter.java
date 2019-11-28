package in.kashewdevelopers.screensavers;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class ColorShifter extends Activity {

    int Red, Green, Blue, Phase, shiftSpeed;
    RelativeLayout displayArea;
    SeekBar speedController;
    boolean controlsVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_shifter);

        Red = Green = Phase = 0;
        Blue = 255;
        controlsVisible = false;
        shiftSpeed = 5;

        displayArea = findViewById(R.id.displayArea);
        displayArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlsVisible) {
                    findViewById(R.id.controlPanel).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.controlPanel).setVisibility(View.VISIBLE);
                }
                controlsVisible = !controlsVisible;
            }
        });


        speedController = findViewById(R.id.seekBar);
        speedController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shiftSpeed = 62 - (progress * 4);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        shiftColor();

        Toast.makeText(this, "Tap on Screen to toggle controls", Toast.LENGTH_SHORT).show();

    }

    void shiftColor() {

        int constant = 10;

        displayArea.setBackgroundColor(Color.argb(255, Red, Green, Blue));

        switch (Phase) {
            case 0:
                Red += constant;
                if (Red >= 255) {
                    Red = 255;
                    Phase = (Phase + 1) % 6;
                }
                break;
            case 1:
                Blue -= constant;
                if (Blue <= 0) {
                    Blue = 0;
                    Phase = (Phase + 1) % 6;
                }
                break;
            case 2:
                Green += constant;
                if (Green >= 255) {
                    Green = 255;
                    Phase = (Phase + 1) % 6;
                }
                break;
            case 3:
                Red -= constant;
                if (Red <= 0) {
                    Red = 0;
                    Phase = (Phase + 1) % 6;
                }
                break;
            case 4:
                Blue += constant;
                if (Blue >= 255) {
                    Blue = 255;
                    Phase = (Phase + 1) % 6;
                }
                break;
            case 5:
                Green -= constant;
                if (Green <= 0) {
                    Green = 0;
                    Phase = (Phase + 1) % 6;
                }
                break;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shiftColor();
            }
        }, shiftSpeed);

    }

}
