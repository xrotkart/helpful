package pl.slivka.starfighter2d;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by Tomek on 2016-08-28.
 */
public class SFGame extends Activity {
    private SFGameView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        gameView = new SFGameView(this);
        setContentView(gameView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int height = SFEngine.display.getHeight() / 4;
        int playableArea = SFEngine.display.getHeight() - height;
        if (y > playableArea){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (x < SFEngine.display.getWidth() / 2) {
                        SFEngine.playerFlightAction = SFEngine.PLAYER_BANK_LEFT_1;
                    } else {
                        SFEngine.playerFlightAction = SFEngine.PLAYER_BANK_RIGHT_1;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    SFEngine.playerFlightAction = SFEngine.PLAYER_RELEASE;
                    break;
            }
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.onResume();
    }

    @Override
    protected  void onPause() {
        super.onPause();
        gameView.onPause();
    }
}
