package com.sead.colourgame;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageButton ibtnPlay;
    private Button btnHigh_score;
    private Button btnQuit;
    private MediaPlayer mainSound;
    private int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainSound = MediaPlayer.create(MainActivity.this, R.raw.main4);
        mainSound.start();
        ibtnPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        btnHigh_score = (Button) findViewById(R.id.btnHigh_score_main);
        btnQuit = (Button) findViewById(R.id.btnQuit_main);

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainSound.stop();
                Intent intent = new Intent(MainActivity.this, InGame.class);
                startActivity(intent);
                finish();
            }
        });

        btnHigh_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHigh_score();
            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogQuit_game();
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        mainSound.seekTo(length);
        mainSound.start();
    }
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        mainSound.pause();
        length = mainSound.getCurrentPosition();
    }
    private void DialogHigh_score(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_high_score);
        dialog.setCanceledOnTouchOutside(false);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((11 * width)/14, (32 * height)/45);

        ListView lvUser = (ListView) dialog.findViewById(R.id.lvUsers);
        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        List<Users> usersArrayList = db.getTOP10Users();
        UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, R.layout.sigle_item_high_score, usersArrayList);
        lvUser.setAdapter(usersAdapter);

        Button btnExit = (Button) dialog.findViewById(R.id.btnExit_High);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void DialogQuit_game(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_quitgame);
        dialog.setCanceledOnTouchOutside(false);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width)/7, (9 * height)/21);


        Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        Button btnNo = (Button) dialog.findViewById(R.id.btnNo);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mainSound.stop();
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
