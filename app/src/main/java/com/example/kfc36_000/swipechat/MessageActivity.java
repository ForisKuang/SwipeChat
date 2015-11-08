package com.example.kfc36_000.swipechat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {

    private float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.sendingOptions);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = ev.getX();
                y1 = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = ev.getX();
                y2 = ev.getY();
                float deltaX = x2-x1;
                float deltaY = y2-y1;
                if(Math.abs(deltaX) > MIN_DISTANCE && deltaX > 0) {
                    // TODO: Discard activity to implemented
                    Toast.makeText(this, "Left to Right swipe [Discard]", Toast.LENGTH_SHORT).show();
                } else if(Math.abs(deltaX) > MIN_DISTANCE && deltaX < 0) {
                    // TODO: Draft Activity to be implemented
                    Toast.makeText(this, "Right to Left swipe [Draft]", Toast.LENGTH_SHORT).show();
                } else if(Math.abs(deltaX) > MIN_DISTANCE && deltaY < 0) {
                    // TODO: implement Send action
                    Toast.makeText(this, "Swipe up [Send]", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onTouchEvent(ev);

    }

}
