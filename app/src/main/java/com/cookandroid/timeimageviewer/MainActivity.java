package com.cookandroid.timeimageviewer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView backText;
    Handler handler = new Handler();
    ImageView imageView;
    ArrayList<Drawable> List = new ArrayList<>();
    BackGround Thread = new BackGround();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List.add(getDrawable(R.drawable.s01));
        List.add(getDrawable(R.drawable.s02));
        List.add(getDrawable(R.drawable.s03));
        imageView = (ImageView) findViewById(R.id.imageView);
        backText = (TextView) findViewById(R.id.backText);
        Button mainButton1 = (Button) findViewById(R.id.mainButton1);
        Button mainButton2 = (Button) findViewById(R.id.mainButton2);

        mainButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread = new BackGround();
                Thread.start();
            }
        });
        mainButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread.interrupt();
            }
        });

    }
    class BackGround extends Thread{
        @Override
        public void run(){
            int index =0;
            int back_value=0;
            while(!isInterrupted()){
                final Drawable drawable = List.get(index%List.size());
                int Back_value = back_value;
                handler.post(new Runnable(){
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                        backText.setText("back_value:"+ Back_value);
                    }
                });
                index++;
                back_value++;
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                    return;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}