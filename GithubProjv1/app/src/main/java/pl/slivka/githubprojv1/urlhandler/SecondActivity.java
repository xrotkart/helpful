package pl.slivka.githubprojv1.urlhandler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import pl.slivka.githubprojv1.R;

/**
 * Created by Tomek on 2016-05-03.
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Uri data = intent.getData();
        List<String> params = data.getPathSegments();
        String first = params.get(0); // "status"
        String second = params.get(1); // "1234"

        Log.d("data", first + " " + second);
    }
}
