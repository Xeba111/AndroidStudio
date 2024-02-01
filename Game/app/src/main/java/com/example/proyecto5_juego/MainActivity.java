package com.example.proyecto5_juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static SingletonHistory mStatusTracker = SingletonHistory.getInstance();
    private Button historyButton,rockButton,paperButton,scissorsButton;
    private ImageView imagePlayer,imageCpu, imageResult;
    private boolean canPlay = true;
    private String names[] = {"Rock", "Paper", "Scissors"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyButton = (Button) findViewById(R.id.historyButton);
        rockButton = (Button) findViewById(R.id.rockButton);
        paperButton = (Button) findViewById(R.id.paperButton);
        scissorsButton = (Button) findViewById(R.id.scissorsButton);
        imagePlayer = (ImageView)findViewById(R.id.imageViewPlayer);
        imageCpu = (ImageView)findViewById(R.id.imageViewCpu);
        imageResult = (ImageView) findViewById(R.id.imageResult);
        imageResult.setVisibility(View.INVISIBLE);

    }

    public void changeActivity(View view){
        /*Start activity*/

        Intent intent = new Intent(MainActivity.this, History.class);
        startActivity(intent);

    }

    public void showRockImage(View view){
        updateViews(0);
    }
    public void showPaperImage(View view){
        updateViews(1);
    }
    public void showScissorsImage(View view){
        updateViews(2);
    }

    public void blockButton(boolean play){
        rockButton.setEnabled(play);
        scissorsButton.setEnabled(play);
        paperButton.setEnabled(play);
    }

    public void playAgain(View view){
        String url = "@drawable/loading";
        int imageResource = getResources().getIdentifier(url,null,getPackageName());
        /*For the image view of the player*/
        Drawable res = getResources().getDrawable(imageResource);
        imagePlayer.setImageDrawable(res);
        imageCpu.setImageDrawable(res);
        imageResult.setImageDrawable(res);
        canPlay = true;
        imageResult.setVisibility(View.INVISIBLE);
        blockButton(canPlay);

    }

    public void updateViews(int result){
        Logic gameController = new Logic(result);
        mStatusTracker.incrementScore(gameController.result);
        /*Loads the image and sets it to the image view of the player*/
        String urlPlayer = gameController.imageChoices[gameController.statusPlayer];
        String urlCPU = gameController.imageChoices[gameController.statusCpu];
        String urlResult = gameController.imageResults[gameController.resultOfGame()];


        int imageResource = getResources().getIdentifier(urlPlayer,null,getPackageName());
        int imageResourceCPU = getResources().getIdentifier(urlCPU,null,getPackageName());
        int imageResourceResult = getResources().getIdentifier(urlResult,null,getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        Drawable resCpu = getResources().getDrawable(imageResourceCPU);
        Drawable resResult = getResources().getDrawable(imageResourceResult);

        imagePlayer.setImageDrawable(res);
        imageCpu.setImageDrawable(resCpu);
        imageResult.setImageDrawable(resResult);

        mStatusTracker.add("Paper vs " + this.names[gameController.statusCpu] + ":" + gameController);
        canPlay = false;
        imageResult.setVisibility(View.VISIBLE);
        blockButton(canPlay);
        Toast.makeText(MainActivity.this,gameController.toString() + "", Toast.LENGTH_SHORT).show();
    }


}