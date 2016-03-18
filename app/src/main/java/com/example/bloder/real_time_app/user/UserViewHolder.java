package com.example.bloder.real_time_app.user;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bloder.real_time_app.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.user)
public class UserViewHolder extends LinearLayout{

    @ViewById(R.id.name) protected TextView name;
    @ViewById(R.id.age) protected TextView age;

    public UserViewHolder(Context context) {
        super(context);
    }

    public void bind(String name, int age) {
        this.name.setText(name);
        this.age.setText(String.valueOf(age));
    }
}
