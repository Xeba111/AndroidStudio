/*
       Proyecto 4 Linear Regression
       Joel del Castillo 211456
       Jose Luis Santillan 211496

 */




package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.logic.LinearRegression;
import com.example.calculator.ui.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewA;
    private TextView textViewB;
    private TextView textViewCorrelation;
    private ScrollView scrollCoordinates;
    private LinearLayout linearLayout;
    private Float coordinateA, coordinateB = 0.0f ;
    private EditText editTextNumberX, editTextNumberY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewA = (TextView) findViewById(R.id.textViewA);
        textViewB = (TextView) findViewById(R.id.textViewB);
        textViewCorrelation = (TextView) findViewById(R.id.textViewCorrelation);
        editTextNumberX = (EditText) findViewById(R.id.editTextNumberX);
        editTextNumberY = (EditText) findViewById(R.id.editTextNumberY);
        scrollCoordinates = (ScrollView) findViewById(R.id.scrollCoordinates);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
    }


    public void add(View view){
 
            String getX= editTextNumberX.getText().toString();
            String getY = editTextNumberY.getText().toString();
            if(!getX.isEmpty()  && !getY.isEmpty()) {
                //Get coordinates
                coordinateA = Float.valueOf(getX);
                coordinateB = Float.valueOf(getY);
                Coordinate coordinate = new Coordinate(this, coordinateA, coordinateB );
                //Cast to string
                String auxA = coordinateA + "";
                String auxB = coordinateB + "";
                //Add to ScrollView
                linearLayout.addView(coordinate);
                //Clear fields
                editTextNumberX.setText("");
                editTextNumberY.setText("");
            }else{
                //Parameters missing
                Toast.makeText(MainActivity.this,"Parameters missing", Toast.LENGTH_SHORT).show();
            }
    }

    public void calculate(View view){
        int count = linearLayout.getChildCount();
        LinkedList<Float> xCoordinates = new LinkedList<>();
        LinkedList <Float> yCoordinates = new LinkedList<>();
        View c  = null;
        Float sum = 0.0f;
        for(int i=0; i<count; i++) {
            c = linearLayout.getChildAt(i);
            Coordinate child = (Coordinate) c;
            if(!child.isRemoved) {
                xCoordinates.add(child.getCoordinateX());
                yCoordinates.add(child.getCoordinateY());
            }
        }
        LinearRegression regression = new LinearRegression(xCoordinates, yCoordinates);
        if(count < 2){
            Toast.makeText(this,"Need more coordinates", Toast.LENGTH_SHORT).show();
        }else {
            textViewA.setText(regression.a + "");
            textViewB.setText(regression.b + "");
            textViewCorrelation.setText(regression.r + "");
        }
    }


    public void clearAll(View view){
        textViewA.setText("");
        textViewB.setText("");
        textViewCorrelation.setText("");
        linearLayout.removeAllViews();
        Toast.makeText(this,"Cleared", Toast.LENGTH_SHORT).show();

    }



}