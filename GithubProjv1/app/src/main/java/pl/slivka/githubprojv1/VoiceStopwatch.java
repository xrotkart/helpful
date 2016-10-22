package pl.slivka.githubprojv1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Tomek on 2016-10-22.
 */
public class VoiceStopwatch extends AppCompatActivity{
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
    Button speakButton;
    ListView mList;
    CountDownTimer cdt;
    TextView tvcdt;
    Integer defaultTimer;
    TextToSpeech t1;
    Boolean isPaused;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch_activity);

        tvcdt = (TextView) findViewById(R.id.tv_cdt);
        defaultTimer = 30000;
        tvcdt.setText("seconds remaining: " + defaultTimer / 1000);

        speakButton = (Button) findViewById(R.id.btn_speak);
        /*speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceRecognitionActivity();
            }
        });*/
        startVoiceRecognitionActivity();

        voiceinputbuttons();

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void informationMenu() {
        startActivity(new Intent("android.intent.action.INFOSCREEN"));
    }

    public void voiceinputbuttons() {
        speakButton = (Button) findViewById(R.id.btn_speak);
        mList = (ListView) findViewById(android.R.id.list);
    }

    public void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        startVoiceRecognitionActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            mList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, matches));

            if (matches.contains("information")) {
                informationMenu();
                startVoiceRecognitionActivity();
            }

            if (matches.contains("start")) {
                isPaused = false;
                cdt = new CountDownTimer(defaultTimer, 1000) {

                    public void onTick(long millisUntilFinished) {
                        if(isPaused)
                        {
                            cancel();
                        }
                        tvcdt.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        tvcdt.setText("done!");
                    }
                };
                cdt.start();
                startVoiceRecognitionActivity();
            } else if (matches.contains("więcej")) {
                defaultTimer = defaultTimer + 5000;
                tvcdt.setText("seconds remaining: " + defaultTimer / 1000);
                startVoiceRecognitionActivity();
            } else if (matches.contains("mniej")) {
                defaultTimer = defaultTimer - 5000;
                tvcdt.setText("seconds remaining: " + defaultTimer / 1000);
                startVoiceRecognitionActivity();
            } else if (matches.contains("czas")) {
                defaultTimer = defaultTimer / 1000;
                t1.speak(defaultTimer.toString(), TextToSpeech.QUEUE_FLUSH, null);
                startVoiceRecognitionActivity();
            } else if (matches.contains("stop")) {
                isPaused = true;
                startVoiceRecognitionActivity();
            } else {
                startVoiceRecognitionActivity();
            }
        } else if(resultCode == RESULT_CANCELED){
            Toast.makeText(VoiceStopwatch.this, "Nic nie wykryto!", Toast.LENGTH_LONG).show();
            Log.d("status", "nie wykryto");
        }
    }
}

