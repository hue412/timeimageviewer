package com.cookandroid.timeimageviewer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int back_value=0;
    TextView backText;
    Handler handler = new Handler();
    ImageView imageView;
    ArrayList<Drawable> List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List.add(getDrawable(R.drawable.s01));
        List.add(getDrawable(R.drawable.s02));
        List.add(getDrawable(R.drawable.s03));
        imageView = (ImageView) findViewById(R.id.imageView);
        backText = (TextView) findViewById(R.id.backText);
        Button mainButton = (Button) findViewById(R.id.mainButton);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackGround newThread = new BackGround();
                newThread.start();
            }
        });

    }
    class BackGround extends Thread{
        @Override
        public void run(){
            int index =0;
            while(true){
                final Drawable drawable = List.get(index%List.size());
                handler.post(new Runnable(){
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                        backText.setText("back_value:"+back_value);
                    }
                });
                index++;
                back_value++;
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

}