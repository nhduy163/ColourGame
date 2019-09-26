package com.sead.colourgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Users> usersList;

    public UsersAdapter(Context context, int layout, List<Users> usersList) {
        this.context = context;
        this.layout = layout;
        this.usersList = usersList;
    }

    @Override
    public int getCount() {
        return usersList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_score = (TextView) view.findViewById(R.id.tv_score);
        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);

        Users users = usersList.get(i);
        tv_name.setText("#"+users.getRank()+"\t\t\t" + users.getName());
        tv_score.setText("Score: "+users.getScore());
        tv_date.setText(users.getDate());

        return view;
    }
}
