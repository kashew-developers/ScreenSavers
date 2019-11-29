package in.kashewdevelopers.screensavers;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import in.kashewdevelopers.screensavers.Fragments.ColorShifterFragment;

public class FragmentHolderActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);

        int screenSaverId = getIntent().getIntExtra("ScreenSaverId", 0);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch ( screenSaverId ){
            case 0:
                fragmentTransaction.replace(R.id.fragmentHolder, new ColorShifterFragment());
                break;
        }

        fragmentTransaction.commit();

    }
}
