package com.example.kylemcnee.botify;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Kyle McNee on 3/2/2016.
 */
public class SongService extends Service {
    final MediaPlayer player = new MediaPlayer();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (player.isPlaying()){
            player.pause();
        }else{
            player.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            String url = "http://archive.org/download/01DiddieWahDiddie/Diddie-Wah-Diddie-Blind-Blake-Eric-Din-M2MM.mp3";
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                }
            });
        } catch (Throwable thr){
        }
        Toast.makeText(SongService.this, "Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }
}
