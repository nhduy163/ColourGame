package com.sead.colourgame;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentLevel2 extends Fragment {

    Result result;

    Question question;
    List<Answer> listAnswer;

    TextView txtQuestion;
    GridView gVAnswer;

    int current = 0;

    String[] color = {
            "#FF0000","#FFFF00","#008000","#FFC0CB","#FF4654EF","#FF6F089B"
    };
    String[] values = {
            "ĐỎ",
            "VÀNG",
            "LỤC",
            "HỒNG",
            "CHÀM",
            "TÍM"
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_game,container, false);
        result = (Result) getActivity();

        Question(view);
        Answer(view);
        final MediaPlayer correctSound = MediaPlayer.create(getActivity(), R.raw.correct);
        final MediaPlayer incorrectSound = MediaPlayer.create(getActivity(), R.raw.incorrect);

        gVAnswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gVAnswer.setEnabled(false);
                if(InGame.flag){
                    if(question.getKey() == listAnswer.get(i).getKey()){
                        result.value(true);
                        correctSound.start();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        correctSound.stop();
                    }else{
                        incorrectSound.start();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        incorrectSound.stop();
                        result.value(false);
                    }
                }
            }
        });
        return view;
    }

    public void Question(View view){
        txtQuestion = (TextView) view.findViewById(R.id.textViewQuestion);

        //Tạo màu chữ ngẫu nhiên cho câu hỏi
        Random randomColor = new Random();
        int localColor = randomColor.nextInt(color.length);
        txtQuestion.setTextColor(Color.parseColor(color[localColor]));

        //Tạo background ngẫu nhiên cho câu hỏi
        Random randomBackground = new Random();
        int localBackground = randomBackground.nextInt(color.length);
        while (localBackground == localColor){
            randomBackground = new Random();
            localBackground = randomBackground.nextInt(color.length);
        }
        //txtQuestion.setBackgroundColor(Color.parseColor(color[localBackground]));

        //Tạo values ngẫu nhiên cho câu hỏi
        Random randomValues = new Random();
        int localValues = randomBackground.nextInt(values.length);
        txtQuestion.setText(values[localValues]);

        //Gán giá trị cho câu hỏi
        question = new Question(color[localColor],localColor);
    }

    public void Answer(View view){
        //gán giá trị cho listAnswer
        listAnswer =  new ArrayList<>();
        String[] colorBg = color;
        String[] newArr = null;
        for(int i = 0; i < values.length; i++){
            //tạo màu ngẫu nhiên cho đáp án
            Random randomColor = new Random();
            int localColor = randomColor.nextInt(colorBg.length);

            Answer as = new Answer(values[i],i,colorBg[localColor]);
            listAnswer.add(as);

            //xóa màu vừa tạo
            for(int j = 0; j < colorBg.length; j++){
                if(j == localColor){
                    newArr = new String[colorBg.length - 1];
                    for(int index = 0; index < j; index++){
                        newArr[index] = colorBg[index];
                    }
                    for(int k = j; k < colorBg.length - 1; k++){
                        newArr[k] = colorBg[k+1];
                    }
                    colorBg = newArr;
                    break;
                }
            }
        }
        gVAnswer = (GridView) view.findViewById(R.id.gridViewAnswer);
        AdapterAnswer adapterAnswer = new AdapterAnswer(getContext(), listAnswer);
        gVAnswer.setAdapter(adapterAnswer);
    }
}
