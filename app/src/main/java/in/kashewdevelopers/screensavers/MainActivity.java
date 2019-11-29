package in.kashewdevelopers.screensavers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import in.kashewdevelopers.screensavers.Fragments.ColorShifterFragment;

public class MainActivity extends FragmentActivity {

    boolean backPressed;
    Toast onBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backPressed = false;
        onBackPressed = Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT);

        final Intent i = new Intent(this, FragmentHolderActivity.class);

        findViewById(R.id.colorShifterBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("ScreenSaverId", 0);
                startActivity(i);
            }
        });

        findViewById(R.id.randomLinesBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("ScreenSaverId", 1);
                startActivity(i);
            }
        });

        findViewById(R.id.straightLinesBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("ScreenSaverId", 2);
                startActivity(i);
            }
        });

        findViewById(R.id.circlesBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("ScreenSaverId", 3);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if( backPressed ) super.onBackPressed();

        backPressed = true;
        onBackPressed.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = false;
            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        onBackPressed.cancel();
        super.onDestroy();
    }

}
