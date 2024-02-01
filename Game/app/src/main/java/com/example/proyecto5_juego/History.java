package com.example.proyecto5_juego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    private static SingletonHistory mStatusTracker = SingletonHistory.getInstance();
    RecyclerView recycler;
    Button clearButton;
    Button backButton;
    Adapter adapter;
    TextView scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recycler = (RecyclerView) findViewById(R.id.RecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        clearButton = (Button) findViewById((R.id.Clear));
        backButton = (Button) findViewById((R.id.Back));
        scores = (TextView) findViewById((R.id.scores));
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                scores.setText(getResults(mStatusTracker.scores));
            }
        }, 10);

        adapter = new Adapter(mStatusTracker.getHistoryList());
        recycler.setAdapter(adapter);
    }

    public void showDialog(View view) {

        // create a new AlertDialog
        AlertDialog.Builder builder =
                new AlertDialog.Builder(History.this);

        AlertDialog alert = builder.create();
        // set the AlertDialog's title
        builder.setMessage("Are you sure you want to clear History?");
        builder.setTitle("Clear all History");

        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ArrayList<String> newHistoryList = new ArrayList<String>();
                adapter.updateData(newHistoryList);
                Toast.makeText(History.this,"Clear: " + mStatusTracker.getHistoryList().size(), Toast.LENGTH_SHORT).show();
                mStatusTracker.clear();
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    public void run() {
                        scores.setText(getResults(mStatusTracker.scores));
                    }
                }, 10);

                adapter = new Adapter(mStatusTracker.getHistoryList());
                recycler.setAdapter(adapter);
            }
        });

        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alert.cancel();
                Toast.makeText(History.this,"Cancel", Toast.LENGTH_SHORT).show();
            }
        } );


        builder.show();
    }

    public void backMain(View view){
        Intent intent = new Intent(History.this, MainActivity.class);
        startActivity(intent);
    }

    public String getResults(int scores[]){
        return (scores[0] + " / " + scores[1] + " / " + scores[2] );
    }

}