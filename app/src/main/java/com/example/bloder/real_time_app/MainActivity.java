package com.example.bloder.real_time_app;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bloder.real_time_app.user.FixUsers;
import com.example.bloder.real_time_app.user.User;
import com.example.bloder.real_time_app.user.UserAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.users) protected RecyclerView users;
    private List<User> userList;
    private Socket socket;{
        try{
            socket = IO.socket(BuildConfig.CHAT_SERVER_ADRESS);
        }catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
    }

    @AfterViews
    protected void afterViews() {
        setupSocket();
    }

    @Background
    protected void setupSocket() {
        configuringSocket();
        setupAdapter();
    }

    @UiThread
    protected void setupAdapter() {
        userList = FixUsers.fixUserInList();
        LinearLayoutManager userLayoutManager = new LinearLayoutManager(getApplicationContext());
        userLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        users.setLayoutManager(userLayoutManager);
        users.setAdapter(new UserAdapter(userList, this));
    }

    private void configuringSocket() {
        socket.on("user-created", onUserData);
        socket.connect();
    }

    @UiThread
    protected void addUser(String name, int age) {
        userList.add(new User(name, age));
        users.getAdapter().notifyItemInserted(userList.size() - 1);
    }

    private Emitter.Listener onUserData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            String name;
            int age;
            try {
                name = data.getString("name");
                age = data.getInt("age");
            } catch (JSONException e) {
                return;
            }

            addUser(name, age);
        }
    };
}
