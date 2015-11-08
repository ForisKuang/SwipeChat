package com.example.kfc36_000.swipechat;

import com.github.clans.fab.*;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {

    private float x1,x2,y1,y2;
    static final int MIN_DISTANCE = 150;

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private EditText messageText;
    private FloatingActionMenu sendOptionsMenu;
    private SendTypes sendType = SendTypes.UPDATE_ONLY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing other values
        messageText =  (EditText)findViewById(R.id.messageBox);
        sendOptionsMenu = (FloatingActionMenu)findViewById(R.id.sendOptions);

        // Initializing the drawer values
        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        sendOptionsMenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    sendOptionsMenu.hideMenu(true);
                } else {
                    sendOptionsMenu.showMenu(true);
                }
            }
        });

        FloatingActionButton burstButton = (FloatingActionButton)findViewById(R.id.burstButton);
        burstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendType = SendTypes.SEND_BURST;
            }
        });
        FloatingActionButton directionalButton = (FloatingActionButton)findViewById(R.id.directionalButton);
        directionalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendType = SendTypes.SEND_DIRECTIONAL;
            }
        });
        FloatingActionButton firstPersonButton = (FloatingActionButton)findViewById(R.id.firstPersonButton);
        firstPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendType = SendTypes.SEND_FIRSTPERSON;
            }
        });


    }

    private void addDrawerItems(){
        String[] optionsArray = {"Settings", "Application Settings", "Discovery Settings", "Profile"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 1:
                        Intent a = new Intent(MessageActivity.this, ApplicationSettings.class);
                        startActivity(a);
                        break;
                    case 2:
                        Intent b = new Intent(MessageActivity.this, DiscoverySettings.class);
                        startActivity(b);
                        break;
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Settings");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("Messenger");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
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
                    messageText.setText("Enter Message Here...");

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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    enum SendTypes{
        SEND_FIRSTPERSON,
        SEND_BURST,
        SEND_DIRECTIONAL,
        UPDATE_ONLY
    }

}
