package in.kashewdevelopers.screensavers.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import in.kashewdevelopers.screensavers.R;

public class ColorShifterFragment extends Fragment {

    private int Red, Green, Blue, Phase, shiftSpeed;
    private RelativeLayout displayArea;
    private SeekBar speedController;
    private boolean controlsVisible;
    private Context context;


    public ColorShifterFragment() { }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_color_shifter, container, false);

        // Initialize Global-Private variables
        Red = Green = Phase = 0;
        Blue = 255;
        controlsVisible = false;
        shiftSpeed = 5;

        // Initialize widgets
        displayArea = view.findViewById(R.id.displayArea);
        speedController = view.findViewById(R.id.seekBar);


        // Show Toast & make widgets clickable only if the fragment is in FragmentHolderActivity
        if (getActivity().getLocalClassName().equals("FragmentHolderActivity")) {

            // Widget OnClick Methods
            displayArea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.findViewById(R.id.controlPanel).setVisibility(controlsVisible ? View.GONE : View.VISIBLE);
                    controlsVisible = !controlsVisible;
                }
            });

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

            // Toast
            Toast.makeText(context, "Tap on Screen to toggle controls", Toast.LENGTH_SHORT).show();
        }

        shiftColor();

        return view;

    }


    private void shiftColor() {

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
