package com.example.android_21_1_ex1.a21__thread_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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
            //버튼을 누르면 startNum()이 실행된다
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

        tvMain.setText("mainNum : " + mainNum);//mainNum출력. 시작값 1 ,startNum이 실행될때마다 +1
        tvSecond.setText("secondNum : " + secondNum);//secondNum출력 시작값 0
    }

    class NewThread extends Thread {

        @Override
        public void run() {
            while (true) {
                secondNum++;//최초의 start()가 실행된 후에 while문ㅇ 의해서 0.5초 간격으로 계속 실행된다
                try {		//+1씩 되고있지만 화면에 표시되는 숫자는 버튼을 누를때변경됨
                    Thread.sleep(500);
                } catch (Exception e) {}

            }
        }

    }


}
