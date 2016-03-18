package com.example.bloder.real_time_app.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.InternalViewHolder> {

    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public InternalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InternalViewHolder();
    }

    @Override
    public void onBindViewHolder(InternalViewHolder holder, int position) {
        holder.bind(userList.get(position).name, userList.get(position).age);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class InternalViewHolder extends RecyclerView.ViewHolder {
        public InternalViewHolder() {
            super(UserViewHolder_.build(context));
        }
        public void bind(String name, int age) {
            ((UserViewHolder) itemView).bind(name, age);
        }
    }
}
