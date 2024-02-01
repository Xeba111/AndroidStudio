package com.example.calculator.ui;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.MainActivity;
import com.google.android.material.button.MaterialButton;


public class Coordinate extends LinearLayout {

    Float x = new Float(0);
    Float y = new Float(0);
    TextView xText;
    TextView yText;
    public boolean isRemoved = false;
    MaterialButton remove;
    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    LayoutParams inParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

    public Coordinate(Context context, Float x, Float y) {
        super(context);
        this.x = x;
        this.y = y;
        params.setMargins(0, 20, 0, 20);
        this.setLayoutParams(params);
        inParams.setMargins(80, 0, 80, 0);
        xText = new TextView(this.getContext());
        yText = new TextView(this.getContext());
        remove = new MaterialButton(this.getContext());
        xText.setText(x.toString());
        xText.setLayoutParams(inParams);
        yText.setText(y.toString());
        yText.setLayoutParams(inParams);
        remove.setText("Remove");
        remove.setLayoutParams(inParams);

        this.addView(xText);
//        this.addView(space);
        this.addView(yText);
        this.addView(remove);

        remove.setOnClickListener(e->{
            //Toast.makeText(this.getContext(),"Debug", Toast.LENGTH_SHORT).show();
            delete();
        });
        this.setBackgroundColor(Color.rgb(147, 141, 242));
    }


    private void delete(){
        this.setVisibility(View.GONE);
        this.x = 0.0f;
        this.y = 0.0f;
        this.isRemoved = true;
    }

    public Float getCoordinateX(){
        return x;
    }

    public Float getCoordinateY(){
        return y;
    }

}
