package pl.slivka.starfighter2d;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class StarFighterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SFEngine.display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent mainMenu = new Intent(StarFighterActivity.this, SFMainMenu.class);
                StarFighterActivity.this.startActivity(mainMenu);
                StarFighterActivity.this.finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, SFEngine.GAME_THREAD_DELAY);
    }
}
