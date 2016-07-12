package com.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_startTask = (Button) findViewById(R.id.btn_startTask);
        assert btn_startTask != null;
        btn_startTask.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        new Thread(new TaskRunnable()).start();
    }

    private class TaskRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5*1000);

                doSubscribe();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void doSubscribe() {
       EventBus.getDefault().post(new MessageEvent("EventBus Message!"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(MainActivity.this);
    }
}
