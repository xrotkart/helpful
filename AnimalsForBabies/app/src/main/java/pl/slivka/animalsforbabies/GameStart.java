package pl.slivka.animalsforbabies;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Tomek on 2016-04-25.
 */
public class GameStart extends Activity {
    String[] animalsArray;
    Animal[] animalFarm;
    Animal first, second;
    Animal[] animalPair;
    TextView tvAnimalName;
    Drawable iv1Drawable, iv2Drawable;
    ImageView iv1, iv2;
    int [] resID, resID2;
    int firstRandom, chosenAnimal;
    MediaPlayer mediaPlayer;
    Random r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        resID2 = new int[]{R.raw.applause, R.raw.wrong_answer};

        iv1 = (ImageView) findViewById(R.id.imageView);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        tvAnimalName = (TextView) findViewById(R.id.tv_animal_name);

        initAnimalTable();
        animalPair = getPair();
        setupGame();



        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvAnimalName.getText().toString().equals(first.getName().toString())){
                    Log.d("click", first.getName().toString());
                    mediaPlayer.stop();
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), resID2[0]);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            animalPair = getPair();
                            setupGame();
                        }
                    });
                } else {
                    Log.d("click", first.getName().toString());
                    mediaPlayer.stop();
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), resID2[1]);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            restartLevel();
                        }
                    });
                }
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvAnimalName.getText().toString().equals(second.getName().toString())){
                    Log.d("click", second.getName().toString());
                    mediaPlayer.stop();
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), resID2[0]);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            animalPair = getPair();
                            setupGame();
                        }
                    });
                } else {
                    Log.d("click", second.getName().toString());
                    mediaPlayer.stop();
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), resID2[1]);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            restartLevel();
                        }
                    });
                }
            }
        });
    }

    private Animal[] getPair(){
        Random r = new Random();
        firstRandom = r.nextInt(4);
        Animal firstAnimal = animalFarm[firstRandom];
        int secondRandom = r.nextInt(4);
        while(secondRandom==firstRandom){
            secondRandom = r.nextInt(4);
        }
        Animal secondAnimal = animalFarm[secondRandom];
        Animal[] aniArray = new Animal[2];
        aniArray[0] = firstAnimal;
        aniArray[1] = secondAnimal;
        return aniArray;
    }

    private void initAnimalTable(){
        Drawable[] animalPictures = new Drawable[4];
        animalPictures[0] = getResources().getDrawable(R.drawable.cat_v1);
        animalPictures[1] = getResources().getDrawable(R.drawable.dog_v1);
        animalPictures[2] = getResources().getDrawable(R.drawable.horse_v1);
        animalPictures[3] = getResources().getDrawable(R.drawable.pig_v1);
        resID = new int[]{R.raw.cat_meow,R.raw.dog_bark,R.raw.horse_whinny,R.raw.pig_eat};
        animalsArray = new String[]{"kot", "pies", "koń", "świnia"};
        animalFarm = new Animal[4];
        for(int f=0; f<animalFarm.length; f++){
            animalFarm[f] = new Animal(f, animalsArray[f], animalPictures[f]);
        }
    }

    private void setupGame(){
        first = animalPair[0];
        second = animalPair[1];
        r = new Random();
        Integer randomNum = r.nextInt(2);
        Log.d("ran", Integer.toString(randomNum));
        if(randomNum.equals(0)){
            tvAnimalName.setText(first.getName());
            chosenAnimal = first.getId();
        } else {
            tvAnimalName.setText(second.getName());
            chosenAnimal = second.getId();
        }
        iv1Drawable = first.getPicture();
        iv1.setImageDrawable(iv1Drawable);
        iv2Drawable = second.getPicture();
        iv2.setImageDrawable(iv2Drawable);
        mediaPlayer=MediaPlayer.create(this, resID[chosenAnimal]);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    private void restartLevel(){
        Log.d("restart", "tutaj");
        mediaPlayer=MediaPlayer.create(getApplicationContext(), resID[chosenAnimal]);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @Override
    public void onBackPressed(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        super.onBackPressed();
    }
}
