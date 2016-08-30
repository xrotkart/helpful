package pl.slivka.starfighter2d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Tomek on 2016-08-27.
 */
public class SFMainMenu extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        SFEngine.musicThread = new Thread() {
            public void run() {
                Intent bgmusic = new Intent(getApplicationContext(), SFMusic.class);
                startService(bgmusic);
                SFEngine.context = getApplicationContext();

            }
        };
        SFEngine.musicThread.start();

        final SFEngine engine = new SFEngine();

        ImageButton start = (ImageButton) findViewById(R.id.btnStart);
        ImageButton exit = (ImageButton) findViewById(R.id.btnExit);

        start.getBackground().setAlpha(SFEngine.MENU_BUTTON_ALPHA);
        start.setHapticFeedbackEnabled(SFEngine.HAPTIC_BUTTON_FEEDBACK);

        exit.getBackground().setAlpha(SFEngine.MENU_BUTTON_ALPHA);
        exit.setHapticFeedbackEnabled(SFEngine.HAPTIC_BUTTON_FEEDBACK);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent game = new Intent(getApplicationContext(), SFGame.class);
                SFMainMenu.this.startActivity(game);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean clean = false;
                clean = engine.onExit(view);
                if (clean) {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                }
            }
        });


    }
}
