package in.kashewdevelopers.screensavers.Fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import in.kashewdevelopers.screensavers.R;

public class CirclesFragment extends Fragment {

    private RelativeLayout displayArea;
    private ImageView drawingArea;
    private SeekBar speedControler;
    private TextView clearButton;

    private Context context;

    private int drawSpeed, screenHeight, screenWidth, circlesDrawn;
    private boolean controlsVisible;

    private Bitmap drawingBitmap;
    private Canvas drawingCanvas;
    private Paint drawingPaint;


    public CirclesFragment() { }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_circles, container, false);

        controlsVisible = false;
        drawSpeed = 100;

        // Initialize widgets
        displayArea = view.findViewById(R.id.displayArea);
        drawingArea = view.findViewById(R.id.drawingArea);
        speedControler = view.findViewById(R.id.seekBar);
        clearButton = view.findViewById(R.id.clearButton);

        // Get Screen Resolution
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenHeight = point.y;
        screenWidth = point.x;
        circlesDrawn = 0;

        // Create Bitmap of the size of the screen
        drawingBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);

        // create canvas on the bitmap
        drawingCanvas = new Canvas(drawingBitmap);
        // set canvas background to Black
        drawingCanvas.drawColor(Color.argb(255, 0, 0, 0));

        // create paint object
        drawingPaint = new Paint();
        drawingPaint.setStyle(Paint.Style.STROKE);
        drawingPaint.setAntiAlias(true);

        drawCircles();

        // show toast and make widgets clickable only if it is in FragmentHolderActivity
        if( getActivity().getLocalClassName().equals("FragmentHolderActivity") ) {

            Toast.makeText(context, "Tap to toggle controls", Toast.LENGTH_SHORT).show();


            // Widget OnClick Methods
            displayArea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.findViewById(R.id.controlPanel).setVisibility(controlsVisible ? View.GONE : View.VISIBLE);
                    controlsVisible = !controlsVisible;
                }
            });

            speedControler.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    drawSpeed = 500 - progress;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawingCanvas.drawColor(Color.argb(255, 0, 0, 0));
                }
            });
        }


        return view;

    }

    private void drawCircles(){

        if( circlesDrawn > 1500 ){
            drawingCanvas.drawColor(Color.argb(255, 0, 0, 0));
            circlesDrawn = 0;
        }

        Random random = new Random();

        int x = random.nextInt(screenWidth);
        int y = random.nextInt(screenHeight);
        int r = random.nextInt(50) + 1;

        int Red = random.nextInt(256);
        int Green = random.nextInt(256);
        int Blue = random.nextInt(256);

        drawingPaint.setColor(Color.argb(255, Red, Green, Blue));
        drawingPaint.setStrokeWidth(random.nextInt(60) + 1);
        drawingCanvas.drawCircle(x, y, r, drawingPaint);
        circlesDrawn++;

        drawingArea.setImageBitmap(drawingBitmap);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawCircles();
            }
        }, drawSpeed);

    }

}
