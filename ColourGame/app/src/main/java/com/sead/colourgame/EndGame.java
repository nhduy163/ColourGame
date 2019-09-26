package com.sead.colourgame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EndGame extends AppCompatActivity {

    private TextView txtScore;
    private ImageButton btnPlay2;
    private Intent intent;
    private Button btnHigh_score, btnQuit;
    private MediaPlayer endSound;
    private int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        txtScore = (TextView) findViewById(R.id.tv_score_end);
        btnPlay2 = (ImageButton) findViewById(R.id.imageButtonPlay2);
        btnHigh_score = (Button) findViewById(R.id.btnHigh_score);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        endSound = MediaPlayer.create(EndGame.this, R.raw.end);
        endSound.start();

        intent = getIntent();
        txtScore.setText(""+intent.getIntExtra("result", 0));

        DialogUser();

        btnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endSound.stop();
                Intent intent = new Intent(EndGame.this, InGame.class);
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
    public void onBackPressed() {
        //super.onBackPressed();
    }
    @Override
    public void onResume(){
        super.onResume();
        endSound.seekTo(length);
        endSound.start();

    }
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        endSound.pause();
        length = endSound.getCurrentPosition();
    }

    private void DialogUser(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_user);
        dialog.setCanceledOnTouchOutside(false);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((6 * width)/7, (10 * height)/21);

        final EditText tfUser_name = (EditText) dialog.findViewById(R.id.tfUser_name);
        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);
        Button btnExit = (Button) dialog.findViewById(R.id.btnExit);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tfUser_name.getText().toString();
                if(name.equals("")){
                    final AlertDialog.Builder aBuilder = new AlertDialog.Builder(EndGame.this);
                    aBuilder.setTitle("Thông báo!");
                    aBuilder.setMessage("Cần nhập tên của bạn!");
                    aBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    aBuilder.show();
                }else{
                    Users user = new Users();
                    intent = getIntent();
                    int score = intent.getIntExtra("result", 0);
                    Date d = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    String date = df.format(d);

                    user = new Users(name,score,date);

                    DatabaseHelper db = new DatabaseHelper(EndGame.this);
                    db.insertUsers(user);
                    dialog.dismiss();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
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
        DatabaseHelper db = new DatabaseHelper(EndGame.this);
        List<Users> usersArrayList = db.getTOP10Users();
        UsersAdapter usersAdapter = new UsersAdapter(EndGame.this, R.layout.sigle_item_high_score, usersArrayList);
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
                endSound.stop();
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
