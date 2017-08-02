package com.example.android_21_1_ex1.myapplication;

import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

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

    private void startNum() {
        mainNum++;

//		NewThread newThread = new NewThread();
        SecondRunnable runnable = new SecondRunnable();
        Thread newThread = new Thread(runnable);
        newThread.setDaemon(true);
        newThread.start();

        tvMain.setText("mainNum : " + mainNum);
        tvSecond.setText("secondNum : " + secondNum);
    }

    class SecondRunnable implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                secondNum++;
                try {
                    Thread.sleep(500);
                } catch (Exception e) {}

            }
        }

    }

//	class NewThread extends Thread {
//
//		@Override
//		public void run() {
//			while (true) {
//				secondNum++;
//				try {
//					Thread.sleep(500);
//				} catch (Exception e) {}
//
//			}
//		}
//
//	}
}

