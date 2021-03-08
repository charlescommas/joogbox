package id.ac.umn.joogbox;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //AnimationDrawable rolling_disk;
    ImageView disk;
    Button btnPlay, btnAbout;
    MediaPlayer player;
    TextView lyrics;
    ScrollView scrollView;
    ObjectAnimator animationLyrics, animationDisk;

    @Override //when app status is paused
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.pause();
            animationLyrics.pause();
            animationDisk.pause();
            btnPlay.setBackgroundResource(R.drawable.play);
            if (isFinishing()) {
                player.stop();
                player.release();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        lyrics = (TextView) findViewById(R.id.lyrics);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        disk = (ImageView) findViewById(R.id.disk);
        disk.setBackgroundResource(R.drawable.rolling_disk);
        //setting Media Player
        player = MediaPlayer.create(this, R.raw.song);
        animationLyrics = ObjectAnimator.ofFloat(lyrics, "translationY", -10000f);
        animationDisk = ObjectAnimator.ofFloat(disk , "rotation", 0f, 64800f);

        //when button About is clicked
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivityForResult(aboutIntent, 1);
            }
        });

        //when media player is finished, change to button play
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnPlay.setBackgroundResource(R.drawable.play);
            }
        });
    }

    public void btnPlayClick(View view){
        if(!player.isPlaying() && !animationDisk.isPaused()){ //if player hasn't started
            //start playing
            player.start();
            animationLyrics.setDuration(180000);
            animationLyrics.start();
            animationDisk.setDuration(180000);
            animationDisk.start();
            btnPlay.setBackgroundResource(R.drawable.pause); //if player has started, change to pause button
        }else if(animationLyrics.isPaused()){ //if player has started, but is pausing
            player.start();
            animationLyrics.resume();
            animationDisk.resume();
            btnPlay.setBackgroundResource(R.drawable.pause);
        }else{ //if player is playing
            //pausing
            player.pause();
            animationLyrics.pause();
            animationDisk.pause();
            btnPlay.setBackgroundResource(R.drawable.play); //change to play button
        }
    }
}
