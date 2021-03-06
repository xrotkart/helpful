package pl.slivka.githubprojv1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Tomek on 2016-10-19.
 */
public class PreGameActivity extends Activity {

    MySurfaceView mySurfaceView;
    Integer incX, incY, lineX, lineY;
    Random r;
    Bitmap mFinalbitmap, mPencilbitmap, mRedline1, mBlueline1, mFingerPrint1, mFingerPrint2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
    }

    class MySurfaceView extends SurfaceView {

        Path path;

        Thread thread = null;
        SurfaceHolder surfaceHolder;
        volatile boolean running = false;

        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        String timer;

        public MySurfaceView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setColor(Color.WHITE);
            path = new Path();
            setWillNotDraw(false);
            setupGame();
            new CountDownTimer(15000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer = String.valueOf(millisUntilFinished / 1000);
                    Log.d("timer", timer);
                    invalidate();
                }

                public void onFinish() {
                }
            }.start();
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawText(timer, 100, 100, paint);
            Log.d("drawing", "...");
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                //path = new Path();
                path.moveTo(lineX, lineY);
            }else if(event.getAction() == MotionEvent.ACTION_MOVE){
                //path.lineTo(20, 40);
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                if((incY % 2) == 0){
                    lineY = 20;
                } else {
                    lineY = 60;
                }
                lineX = lineX + incX;
                path.lineTo(lineX, lineY);
                incX = incX + 1;
                incY = incY + 1;
            }

            if(path != null){
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawPath(path, paint);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            return true;
        }

        private void setupGame(){
            incX = 1;
            incY = 1;
            lineX = 20;
            lineY = 20;


        }

    }
}
