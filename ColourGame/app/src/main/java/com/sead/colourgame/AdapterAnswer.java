package com.sead.colourgame;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterAnswer  extends BaseAdapter{

    Context context;
    private List<Answer> answers;

    View view;
    LayoutInflater layoutInflater;


    public AdapterAnswer(Context context, List<Answer> answers) {
        this.context = context;
        this.answers = answers;
    }

    public Context getContext() {
        return context;
    }

    public List<Answer> getAnswers() {
        return answers;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item_answer,null);
            TextView txtAnswer = (TextView) view.findViewById(R.id.textViewAnswer);

            //SetText values cho đáp án
            txtAnswer.setText(answers.get(i).getValues());
            //SetBackground cho đáp  án
            //txtAnswer.setBackgroundColor(Color.parseColor(answers.get(i).getColor()));
            int color = Color.parseColor(answers.get(i).getColor());
            LayerDrawable layerDrawable = (LayerDrawable) view.getResources().getDrawable(R.drawable.circle);
            GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.shape_id);
            gradientDrawable.setColor(color);
            txtAnswer.setBackgroundResource(R.drawable.circle);
        }

        return view;
    }
}
