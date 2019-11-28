package in.kashewdevelopers.screensavers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RandomLines extends Activity {

    RelativeLayout displayArea;
    ImageView drawingArea;
    Bitmap drawingBitmap;
    Canvas drawingCanvas;
    Paint drawingPaint;
    SeekBar speedControler;
    TextView clearButton;

    int screenHeight, screenWidth, linesDrawn, drawSpeed;

    boolean controlsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_lines);

        controlsVisible = false;
        drawSpeed = 100;

        // Initialize widgets
        displayArea = findViewById(R.id.displayArea);
        drawingArea = findViewById(R.id.drawingArea);
        speedControler = findViewById(R.id.seekBar);
        clearButton = findViewById(R.id.clearButton);

        // Get Screen Resolution
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        screenHeight = point.y;
        screenWidth = point.x;
        linesDrawn = 0;

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

        drawLine();

        Toast.makeText(this, "Tap to toggle controls", Toast.LENGTH_LONG).show();


        // Widget OnClick Methods
        displayArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( controlsVisible ) findViewById(R.id.controlPanel).setVisibility(View.GONE);
                else findViewById(R.id.controlPanel).setVisibility(View.VISIBLE);
                controlsVisible = ! controlsVisible;
            }
        });

        speedControler.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                drawSpeed = 350 - progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingCanvas.drawColor(Color.argb(255, 0, 0, 0));
            }
        });

    }

    public void drawLine(){

        if( linesDrawn > 1500 ){
            drawingCanvas.drawColor(Color.argb(255, 0, 0, 0));
            linesDrawn = 0;
        }

        Random random = new Random();

        int startX = random.nextInt(screenWidth), startY = random.nextInt(screenHeight);
        int stopX = random.nextInt(screenWidth), stopY = random.nextInt(screenHeight);

        int Red = random.nextInt(256);
        int Green = random.nextInt(256);
        int Blue = random.nextInt(256);

        drawingPaint.setColor(Color.argb(255, Red, Green, Blue));
        drawingPaint.setStrokeWidth(random.nextInt(6) + 1);
        drawingCanvas.drawLine(startX, startY, stopX, stopY, drawingPaint);
        linesDrawn++;

        drawingArea.setImageBitmap(drawingBitmap);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawLine();
            }
        }, drawSpeed);

    }

}
