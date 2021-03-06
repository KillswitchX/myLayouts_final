package com.icesi.ej;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {

    WaveLoadingView waveLoadingView;

    private Handler mHandler;
    //SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();

        //seekBar = findViewById(R.id.scanning_apps_seekbar);
        waveLoadingView = findViewById(R.id.scanning_apps_wave_loading);

        waveLoadingView.setProgressValue(0);




    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= 100; i++) {
                    final int currentProgressCount = i;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Post updates to the User Interface

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            waveLoadingView.startAnimation();
                            waveLoadingView.setProgressValue(currentProgressCount);
                            if(currentProgressCount < 55){
                                waveLoadingView.setBottomTitle(String.format("%d%%", currentProgressCount));
                                waveLoadingView.setCenterTitle("");
                                waveLoadingView.setTopTitle("");
                            }
                            else if(currentProgressCount < 85){
                                waveLoadingView.setBottomTitle("");
                                waveLoadingView.setCenterTitle(String.format("%d%%", currentProgressCount));
                                waveLoadingView.setTopTitle("");
                            }
                            else{
                                waveLoadingView.setBottomTitle("");
                                waveLoadingView.setCenterTitle("");
                                waveLoadingView.setTopTitle(String.format("%d%%", currentProgressCount));
                            }
                            if(currentProgressCount == 100){
                                waveLoadingView.endAnimation();
                                Toast.makeText(getApplicationContext(), "Aplicaciones escaneadas con éxito", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        }).start();

    }
}
