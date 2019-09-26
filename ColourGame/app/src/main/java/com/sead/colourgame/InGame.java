package com.sead.colourgame;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InGame extends AppCompatActivity implements Result{

    int  score = 0;
    static boolean flag;
    FragmentManager fragmentManager = getSupportFragmentManager();
    Result result;
    ProgressBar pbTimer;
    MediaPlayer incorrect;
    TextView tvScore;

    CountDownTimer countDownTimer1 = new CountDownTimer(2500, 20) {
        @Override
        public void onTick(long l) {
            pbTimer = (ProgressBar) findViewById(R.id.progressBar);
            int current = pbTimer.getProgress();
            pbTimer.setProgress(current + 1);
        }
        @Override
        public void onFinish() {
            flag = false;
            incorrect.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            incorrect.stop();
            result = (Result) InGame.this;
            result.value(false);
        }
    };

    CountDownTimer countDownTimer2 = new CountDownTimer(3000, 20) {
        @Override
        public void onTick(long l) {
            pbTimer = (ProgressBar) findViewById(R.id.progressBar);
            int current = pbTimer.getProgress();
            pbTimer.setProgress(current + 1);
        }
        @Override
        public void onFinish() {
            flag = false;
            incorrect.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            incorrect.stop();
            result = (Result) InGame.this;
            result.value(false);
        }
    };

    CountDownTimer countDownTimer3 = new CountDownTimer(3500, 20) {
        @Override
        public void onTick(long l) {
            pbTimer = (ProgressBar) findViewById(R.id.progressBar);
            int current = pbTimer.getProgress();
            pbTimer.setProgress(current + 1);
        }
        @Override
        public void onFinish() {
            flag = false;
            incorrect.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            incorrect.stop();
            result = (Result) InGame.this;
            result.value(false);
        }
    };

    CountDownTimer countDownTimer4 = new CountDownTimer(4000, 20) {
        @Override
        public void onTick(long l) {
            pbTimer = (ProgressBar) findViewById(R.id.progressBar);
            int current = pbTimer.getProgress();
            pbTimer.setProgress(current + 1);
        }
        @Override
        public void onFinish() {
            flag = false;
            incorrect.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            incorrect.stop();
            result = (Result) InGame.this;
            result.value(false);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        tvScore = (TextView) findViewById(R.id.tv_score);
        incorrect = MediaPlayer.create(InGame.this, R.raw.incorrect);

        flag = true;
        countDownTimer2.start();
        ReplaceFragment1();
    }

    @Override
    public void onResume(){
        super.onResume();
        incorrect.setVolume(100, 100);
    }
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        incorrect.setVolume(0,0);
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
    @Override
    public void value(boolean b) {
        if(b){
            if(score < 5){
                pbTimer.setProgress(0);
                countDownTimer2.cancel();
                score++;
                ReplaceFragment1();
                countDownTimer2.start();
            }else{
                if(score < 10){
                    pbTimer.setProgress(0);
                    countDownTimer2.cancel();
                    score++;
                    ReplaceFragment2();
                    countDownTimer2.start();
                }else{
                    if(score < 15){
                        pbTimer.setMax(170);
                        pbTimer.setProgress(0);
                        countDownTimer2.cancel();
                        countDownTimer4.cancel();
                        score++;
                        ReplaceFragment3();
                        countDownTimer4.start();
                    }else{
                        if(score < 20){
                            pbTimer.setProgress(0);
                            countDownTimer4.cancel();
                            score++;
                            ReplaceFragment4();
                            countDownTimer4.start();
                        }else{
                            if(score < 25){
                                pbTimer.setMax(100);
                                pbTimer.setProgress(0);
                                countDownTimer4.cancel();
                                countDownTimer1.cancel();
                                score++;
                                ReplaceFragment1();
                                countDownTimer1.start();
                            }else{
                                if(score < 30){
                                    pbTimer.setProgress(0);
                                    countDownTimer1.cancel();
                                    score++;
                                    ReplaceFragment2();
                                    countDownTimer1.start();
                                }else{
                                    if(score < 35){
                                        pbTimer.setMax(150);
                                        pbTimer.setProgress(0);
                                        countDownTimer1.cancel();
                                        countDownTimer3.cancel();
                                        score++;
                                        ReplaceFragment3();
                                        countDownTimer3.start();
                                    }else{
                                        pbTimer.setProgress(0);
                                        countDownTimer3.cancel();
                                        score++;
                                        ReplaceFragment4();
                                        countDownTimer3.start();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else{
            countDownTimer1.cancel();
            countDownTimer2.cancel();
            countDownTimer3.cancel();
            countDownTimer4.cancel();
            Intent intent = new Intent(InGame.this, EndGame.class);
            intent.putExtra("result", score);
            startActivity(intent);
            finish();
        }
        tvScore.setText(score+"");
    }

    public void ReplaceFragment1(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentLevel1 fragmentLevel1 = new FragmentLevel1();
        fragmentTransaction.replace(R.id.fragment, fragmentLevel1,"fragLevel1");
        fragmentTransaction.commit();
    }
    public void ReplaceFragment2(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentLevel2 fragmentLevel2 = new FragmentLevel2();
        fragmentTransaction.replace(R.id.fragment, fragmentLevel2,"fragLevel2");
        fragmentTransaction.commit();
    }
    public void ReplaceFragment3(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentLevel3 fragmentLevel3 = new FragmentLevel3();
        fragmentTransaction.replace(R.id.fragment, fragmentLevel3,"fragLevel3");
        fragmentTransaction.commit();
    }
    public void ReplaceFragment4(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentLevel4 fragmentLevel4 = new FragmentLevel4();
        fragmentTransaction.replace(R.id.fragment, fragmentLevel4,"fragLevel4");
        fragmentTransaction.commit();
    }

}
