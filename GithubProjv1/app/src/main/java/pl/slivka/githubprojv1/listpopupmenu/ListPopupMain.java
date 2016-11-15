package pl.slivka.githubprojv1.listpopupmenu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import pl.slivka.githubprojv1.R;

/**
 * Created by Tomek on 2016-11-15.
 */
public class ListPopupMain extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view (which contains a PopupListFragment)
        setContentView(R.layout.popup_main);
    }
}
