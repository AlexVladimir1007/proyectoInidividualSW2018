package com.example.alexvladimir.myfirstapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;


public class music extends AppCompatActivity {

    Button play_pause;
    MediaPlayer mp;
    MediaPlayer mp2;
    int pause;
    int c=0;
    private double startTime = 0;
    private double finalTime = 0;


    private Handler myHandler = new Handler();;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3;


    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        tx1 = (TextView)findViewById(R.id.textView2);
        tx2 = (TextView)findViewById(R.id.textView3);
        tx3 = (TextView)findViewById(R.id.textView4);
        tx3.setText("Ashes Remain With out You.mp3");
        mp= MediaPlayer.create(this,R.raw.musicplaylist);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
    }


    public void pause(View view){
        if(mp !=null) {
            mp.pause();
            pause = mp.getCurrentPosition();
            Toast.makeText(music.this, "Pause", Toast.LENGTH_SHORT).show();
        }
    }

    public void play(View view){

        finalTime = mp.getDuration();
        startTime = mp.getCurrentPosition();



        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }

        tx2.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );

        tx1.setText(String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                startTime))));


        if(!mp.isPlaying()){
           mp.seekTo(pause);
           mp.start();
           Toast.makeText(music.this, "Play again", Toast.LENGTH_SHORT).show();
       }else if(c==0){
           mp.pause();
           mp.stop();
           mp= MediaPlayer.create(this,R.raw.musicplaylist);
           c++;
           mp.start();
            tx3.setText("Ashes Remain With out You.mp3");
           Toast.makeText(music.this, "Play", Toast.LENGTH_SHORT).show();
       }

        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateSongTime,100);;

    }


    public void stop(View view){
        mp.pause();
        mp.stop();
        mp= MediaPlayer.create(this,R.raw.musicplaylist);
        c=0;
        Toast.makeText(music.this, "Stop", Toast.LENGTH_SHORT).show();
        tx3.setText("Ashes Remain With out You.mp3");
    }



    public void forward(View view) {
        if (c == 1) {
            mp.pause();
            mp.stop();
            mp = MediaPlayer.create(this, R.raw.playlist2);
            tx3.setText("Anrix Nova.mp3");
            mp.start();
            Toast.makeText(music.this, "Play", Toast.LENGTH_SHORT).show();
            c++;
        }else if(c==2){
            mp.pause();
            mp.stop();
            mp = MediaPlayer.create(this, R.raw.musicplaylist3);
            tx3.setText("Allen Walker Faded.mp3");
            mp.start();
            Toast.makeText(music.this, "Play", Toast.LENGTH_SHORT).show();
            c++;
    }else if(c>=3){
            mp.pause();
            mp.stop();
            c=0;
            Toast.makeText(music.this, "play List End", Toast.LENGTH_SHORT).show();
        }else if(!mp.isPlaying()){
            mp.seekTo(pause);
            mp.start();
            Toast.makeText(music.this, "Play again", Toast.LENGTH_SHORT).show();
        }


    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mp.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };


}
