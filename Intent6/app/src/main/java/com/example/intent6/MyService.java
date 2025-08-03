package com.example.intent6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    MediaPlayer mymedia;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mymedia = MediaPlayer.create(this, R.raw.sound);
        mymedia.setLooping(true);
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mymedia != null) {
            if (mymedia.isPlaying()) {
                mymedia.pause();
            } else {
                mymedia.start();
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mymedia != null && mymedia.isPlaying()) {
            mymedia.stop();
            mymedia.release();
            mymedia = null;
        }
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}