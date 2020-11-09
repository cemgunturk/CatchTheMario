package com.example.catchthemario;

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
    ImageView mario1; ImageView mario2; ImageView mario3; ImageView mario4; ImageView mario5; ImageView mario6;
    ImageView mario7; ImageView mario8; ImageView mario9; ImageView mario10; ImageView mario11; ImageView mario12;
    ImageView[] images;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        hideImages();

        new CountDownTimer(20000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time : " + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);

                for(ImageView image : images){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure restart game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Your Score : " + score,Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();

    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : images){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(12);
                images[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }

    public void initialize(){
        timeText = findViewById(R.id.textTime);
        scoreText = findViewById(R.id.textScore);
        score = 0;
        mario1 = findViewById(R.id.mario1); mario2 = findViewById(R.id.mario2); mario3 = findViewById(R.id.mario3); mario4 = findViewById(R.id.mario4);
        mario5 = findViewById(R.id.mario5); mario6 = findViewById(R.id.mario6); mario7 = findViewById(R.id.mario7); mario8 = findViewById(R.id.mario8);
        mario9 = findViewById(R.id.mario9); mario10 = findViewById(R.id.mario10); mario11 = findViewById(R.id.mario11); mario12 = findViewById(R.id.mario12);

        images = new ImageView[]{mario1,mario2,mario3,mario4,mario5,mario6,mario7,mario8,mario9,mario10,mario11,mario12};
    }

    public void increaseScore(View view){
        score++;
        scoreText.setText("Score : "+ score);
    }
}
