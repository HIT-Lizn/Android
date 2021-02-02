package com.example.jnitest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Context appcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        // mediaplayer example
        try {
            appcontext = this.getApplicationContext();
            surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(@NonNull SurfaceHolder holder) {
                    mediaPlayer = MediaPlayer.create(appcontext, R.raw.lizn);
                    mediaPlayer.setDisplay(surfaceHolder);
                    surfaceHolder.setKeepScreenOn(true);
                    surfaceHolder.setFixedSize(1200, 900);
                }



                @Override
                public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                else {
                    mediaPlayer.start();
                }
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
