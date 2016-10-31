package pl.slivka.githubprojv1;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Tomek on 2016-10-31.
 */
public class DateTimePickerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DateTimePicker dtp = new DateTimePicker(this);
        setContentView(dtp);
    }
}
