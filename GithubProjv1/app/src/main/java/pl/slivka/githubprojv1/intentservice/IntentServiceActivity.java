package pl.slivka.githubprojv1.intentservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import pl.slivka.githubprojv1.R;

/**
 * Created by Tomek on 2016-10-23.
 */
public class IntentServiceActivity extends Activity {
    // Array of countries
    String[] mCountry = new String[] {
            "India",
            "Pakistan",
            "Bangladesh",
            "Srilanka",
            "Nepal",
            "China",
            "Japan",
            "South Korea",
            "North Korea",
            "Afghanistan"
    };

    Spinner mSprCountry;
    TextView mTvCapital;
    ArrayAdapter<String> mAdapter;
    Intent mServiceIntent;
    CapitalReceiver mReceiver;
    IntentFilter mFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_service_activity);

        // Adapter for Country Spinner
        mAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.intent_service_spinner_layout, mCountry);

        // Getting reference to TextView
        mTvCapital = (TextView) findViewById(R.id.tv_capital);

        // Getting reference to Country Spinner
        mSprCountry = (Spinner) findViewById(R.id.spr_country);

        // Setting adapter for the Country Spinner
        mSprCountry.setAdapter(mAdapter);

        // Creating an intent service
        mServiceIntent = new Intent(getApplicationContext(), CapitalService.class);

        mSprCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                if(arg1!=null){
                    String country = ((TextView)arg1).getText().toString();
                    mServiceIntent.putExtra(Constants.EXTRA_COUNTRY, country);

                    // Starting the CapitalService to fetch the capital of the country
                    startService(mServiceIntent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // Instantiating BroadcastReceiver
        mReceiver = new CapitalReceiver();

        // Creating an IntentFilter with action
        mFilter = new IntentFilter(Constants.BROADCAST_ACTION);

        // Registering BroadcastReceiver with this activity for the intent filter
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, mFilter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    // Defining a BroadcastReceiver
    private class CapitalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String capital = intent.getStringExtra(Constants.EXTRA_CAPITAL);
            mTvCapital.setText("Capital : " + capital);
        }
    }
}
