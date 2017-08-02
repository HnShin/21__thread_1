package com.example.android_21_1_ex1.myapplication;

import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    private int mainNum;
    private int secondNum;
    private TextView tvMain, tvSecond;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMain = (TextView) findViewById(R.id.tv_main_thread);
        tvSecond = (TextView) findViewById(R.id.tv_second_thread);
        btnStart = (Button) findViewById(R.id.bt_start);

        btnStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startNum();
            }
        });

    }

    private void startNum(){
        mainNum++;

        NewThread newThread = new NewThread();
        newThread.setDaemon(true);
        newThread.start();

        tvMain.setText("mainNum : " + mainNum);
        tvSecond.setText("secondNum : " + secondNum);
    }



    Handler mainHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                tvSecond.setText("secondNum : " + secondNum);
                Log.i(TAG, "secondNum in handler : " + secondNum);
            }
        }
    };

    class NewThread extends Thread {

        @Override
        public void run() {
            while (true) {
                secondNum++;
                Log.i(TAG, "secondNum in thread : " + secondNum);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
//				Message msg = new Message();
                Message msg = Message.obtain();
                msg.what = 0;
                msg.arg1 = 0;
                msg.arg2 = 0;
                msg.obj = null;
                mainHandler.sendMessage(msg);
//				mainHandler.sendEmptyMessage(0);
            }
        }

    }
}
