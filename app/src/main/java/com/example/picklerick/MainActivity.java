package com.example.picklerick;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int score;
    ImageView image;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timeText=findViewById(R.id.textView);
        scoreText=findViewById(R.id.textView2);
        image=findViewById(R.id.imageView);
        image2=findViewById(R.id.imageView2);
        image3=findViewById(R.id.imageView3);
        image4=findViewById(R.id.imageView4);
        image5=findViewById(R.id.imageView5);
        image6=findViewById(R.id.imageView6);
        image7=findViewById(R.id.imageView7);
        image8=findViewById(R.id.imageView8);
        image9=findViewById(R.id.imageView9);
        imageArray=new ImageView[] {image,image2,image3,image4,image5,image6,image7,image8,image9};


        hideImages();
        score=0;
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                timeText.setText("Time is up");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure after last time?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent= getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_SHORT).show();

                    }
                });
                alert.show();

            }
        }.start();

    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score: "+score);
    }
    public void hideImages(){
        handler =new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i= random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}
