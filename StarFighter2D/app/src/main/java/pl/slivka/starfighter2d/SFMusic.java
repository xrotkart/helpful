package pl.slivka.starfighter2d;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Tomek on 2016-08-28.
 */
public class SFMusic extends Service {
    public static boolean isRunning = false;
    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        setMusicOptions(this, SFEngine.LOOP_BACKGROUND_MUSIC, SFEngine.R_VOLUME, SFEngine.L_VOLUME, SFEngine.SPLASH_SCREEN_MUSIC);
    }

    public void setMusicOptions(Context context, boolean isLooped, int rVolume, int lVolume, int soundFile) {
        player = MediaPlayer.create(context, soundFile);
        player.setLooping(isLooped);
        player.setVolume(rVolume, lVolume);
    }
    public void onStart(Intent intent, int startId) {
    }

    public void onStop() {
        isRunning = false;
    }

    public void onPause() {
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            player.start();
            isRunning = true;
        } catch (Exception e) {
            isRunning = false;
            player.stop();
        }
        return 1;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {
        player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public IBinder onUnBind(Intent intent) {
        return null;
    }
}
