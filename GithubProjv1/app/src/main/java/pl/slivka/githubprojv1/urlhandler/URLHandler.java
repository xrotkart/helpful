package pl.slivka.githubprojv1.urlhandler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.slivka.githubprojv1.R;

public class URLHandler extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlhandler);

        TextView uri=(TextView)findViewById(R.id.uri);
        Button btnUrl = (Button) findViewById(R.id.btn_url);
        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://commonsware.com/sample")));
            }
        });

        if (Intent.ACTION_MAIN.equals(getIntent().getAction())) {
            String intentUri=(new Intent("com.commonsware.android.MY_ACTION"))
                    .toUri(Intent.URI_INTENT_SCHEME)
                    .toString();

            uri.setText(intentUri);
            Log.w("URLHandler", intentUri);
        }
        else {
            Uri data=getIntent().getData();

            if (data==null) {
                uri.setText("Got com.commonsware.android.MY_ACTION Intent");
            }
            else {
                uri.setText(getIntent().getData().toString());
            }
        }
    }
}
