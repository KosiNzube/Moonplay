package com.mobile.app.moonplay.ui.mediapicker;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.mobile.app.moonplay.R;
import com.mobile.app.moonplay.ui.mediapicker.chooser.MediaChooserFragment;
import com.mobile.app.moonplay.ui.mediapicker.chooser.MediaEntry;
import com.mobile.app.moonplay.util.Logging;

public class MediaPickerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    //region Views

    /**
     * Bottom Navigation of the media picker activity
     */
    BottomNavigationView bottomNav;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //do normal activity stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mediapicker_activity);

        //get views
        bottomNav = findViewById(R.id.mediapicker_navigation_bar);

        //set this activity as navigation listener
        bottomNav.setOnNavigationItemSelectedListener(this);

        //add badge on "more" if a update is available

        //show media chooser songsfragment for VIDEO by default
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mediapicker_content_container, new MediaChooserFragment(MediaEntry.MediaKind.VIDEO))
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        //create songsfragment to show
        Fragment contentFragment = null;
        switch (menuItem.getItemId())
        {
            case R.id.mediapicker_navigation_target_videos:
            {
                //show a media chooser songsfragment for VIDEO
                contentFragment = new MediaChooserFragment(MediaEntry.MediaKind.VIDEO);
                break;
            }
            case R.id.mediapicker_navigation_target_music:
            {
                //show a media chooser songsfragment for MUSIC
                contentFragment = new MediaChooserFragment(MediaEntry.MediaKind.MUSIC);
                break;
            }
            case R.id.mediapicker_navigation_target_more:
            {
                //show "more" page
                contentFragment = new MediaChooserFragment(MediaEntry.MediaKind.MUSIC);
                break;
            }
        }

        //abort if no songsfragment was found for this nav item
        if (contentFragment == null)
        {
            Logging.logE("did not find a songsfragment for menu item id= %d", menuItem.getItemId());
            return false;
        }

        //show songsfragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mediapicker_content_container, contentFragment)
                .commit();
        return true;
    }
}
