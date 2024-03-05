package com.example.vocalcoach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class AudioPlayerActivity extends AppCompatActivity {

    TextView taskName;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private String audioFile;
    Handler handler = new Handler();
    String task_url;
    SeekBar seekBar;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        taskName = (TextView) findViewById(R.id.taskName);
        seekBar = findViewById(R.id.seekBar);
        desc = findViewById(R.id.description);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String name = extras.getString("TASK_NAME");
        String type = extras.getString("TASK_TYPE");
        taskName.setText(name);
        if (type.equals("1")) {
            task_url = "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D1%81%D0%BE%D0%B3%D0%BB%D0%B0%D1%81%D0%BD%D1%8B%D0%B5-%D1%81%D0%BB%D0%BE%D0%B3%D0%B8.mp3?alt=media&token=2f9cc6ed-abe7-4285-b5cc-8629e4b03708";
            //СОГЛАСНЫЕ СЛОГИ
            desc.setText("С КАЖДОЙ ИЗ ГЛАСНЫХ: \nУ О А Э Ы И Е Я Ю \nПО ОЧЕРЕДИ ПРОГОВАРИВАЮТСЯ СОГЛАСНЫЕ: \nКШ МН БВ ПРГ КРМТ ЗКЛМНР ЛМН ТСЛ ВГРН");
        }
        if (type.equals("2")){
            task_url = "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D0%BF%D1%83%D0%B7%D1%8B%D1%80%D0%B5%D0%BA.mp3?alt=media&token=b3cbafb5-81fa-441b-93f4-f230b4e15c85";
            //ПУЗЫРЁК
            desc.setText("ВЫПОЛНЯЕТСЯ В ПАРЕ С УПРАЖНЕНИЕМ ГОРИЗОНТАЛЬ/ВЕРТИКАЛЬ. НЕОБХОДИМО СЛЕДИТЬ ЗА РОВНОЙ ОСАНКОЙ");
        }
        if (type.equals("3")){
            task_url = "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D0%B3%D0%BE%D1%80%D0%B8%D0%B7%D0%BE%D0%BD%D1%82%D0%B0%D0%BB%D1%8C%20%D0%B2%D0%B5%D1%80%D1%82%D0%B8%D0%BA%D0%B0%D0%BB%D1%8C.mp3?alt=media&token=429d8f25-a408-439d-9781-f0637272fdb2";
            //ГОРИЗОНТАЛЬ/ВЕРТИКАЛЬ
            desc.setText("ВЫПОЛНЯЕТСЯ В ПАРЕ С УПРАЖНЕНИЕМ ПУЗЫРЁК. НЕОБХОДИМО СЛЕДИТЬ ЗА РОВНОЙ ОСАНКОЙ");
        }
        if (type.equals("4")){
            task_url = "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D0%BF%D1%80%D0%BE%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B0-%D1%80%D1%8F%D0%B4%D0%BE%D0%B2-%D0%B3%D0%BB%D0%B0%D1%81%D0%BD%D1%8B%D1%85.mp3?alt=media&token=09901da1-c27e-4463-9c70-63f5454b31a0";
            //РЯДЫ ГЛАСНЫХ
            desc.setText("ПО ОЧЕРЕДИ СЛИТНО ПРОГОВАРИВАЮТСЯ РЯДЫ ГЛАСНЫХ: \nА - О - А - Э - А - И - А - У \nО - А - О - Э - О - И - О - У \nЭ - И - Э - О - Э - А - Э - У \nУ - О - У - Э - У - А - У - И");
        }
        if (type.equals("5")){
            task_url = "https://firebasestorage.googleapis.com/v0/b/vocalcoach-e604a.appspot.com/o/%D1%81%D0%BE%D0%B3%D0%BB%D0%B0%D1%81%D0%BD%D1%8B%D0%B5-%D0%B3%D1%80%D1%83%D0%BF%D0%BF%D1%8B.mp3?alt=media&token=df1f48bf-6df4-4056-8087-01904e5f9687";
            //ГРУППЫ СОГЛАСНЫХ
            desc.setText("СОГЛАСНЫЕ ПРОИЗНОСЯТСЯ ПОДРЯД, БЕЗ ГЛАСНЫХ, НА ОПОРЕ. НЕОБХОДИМА ПРЕДВАРИТЕЛЬНАЯ РАЗМИНКА РЕЧЕВОГО АППАРАТА: БКФ\n" +
                    "ДЧП\n" +
                    "ЗРП\n" +
                    "ЩМВ\n" +
                    "ВШЛ\n" +
                    "МРЬБ\n" +
                    "ЖТХ\n" +
                    "СЛБ\n" +
                    "ГВК\n" +
                    "СЙЖ");
        }

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaController = new MediaController(this);

        try {
            mediaPlayer.setDataSource(task_url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("AUDIOPLAYER", "Could not open file " + audioFile + " for playback.", e);
        }

        seekBar.setMax(mediaPlayer.getDuration());
        seekUpdate();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (b) {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer!=null){
                    mediaPlayer.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer!=null){
                    mediaPlayer.start();
                }
            }
        });
    }
    Runnable run= new Runnable() {
        @Override
        public void run() {
            seekUpdate();
        }
    };
    public void seekUpdate(){
        if (mediaPlayer!=null){
            int pos = mediaPlayer.getCurrentPosition();
            int dur = mediaPlayer.getDuration();

            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(run, 100);
        }
    }
    public void audioPlay(View view){
        seekUpdate();
        if (mediaPlayer!=null){
            mediaPlayer.start();
            seekBar.setMax(mediaPlayer.getDuration());
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
        }
    }
    public void audioPause(View view){
        if (mediaPlayer!=null) {
            mediaPlayer.pause();
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //the MediaController will hide after 3 seconds - tap the screen to make it appear again
        mediaController.show();
        return false;
    }

    //--MediaPlayerControl methods----------------------------------------------------

    public void pause() {
        mediaPlayer.pause();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        return 0;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }
    private void clearMediaPlayer() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
    //--------------------------------------------------------------------------------

    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("TAG", "onPrepared");
        mediaController.setMediaPlayer((MediaController.MediaPlayerControl) this);
        mediaController.setAnchorView(findViewById(R.id.audioView));

        handler.post(new Runnable() {
            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}