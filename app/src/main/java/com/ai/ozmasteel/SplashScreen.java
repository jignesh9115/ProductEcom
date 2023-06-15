package com.ai.ozmasteel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static final int DIALOG_ERROR_CONNECTION = 1;

    ImageView imageicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageicon=(ImageView)findViewById(R.id.imageicon);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);

        imageicon.startAnimation(animation);

        //=======================SET TITLE BAR CODE START======================

        Window window = getWindow();
        window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      //  window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        //=======================SET TITLE BAR CODE END========================

       /* if (!isOnline(this))
        {
            showDialog(DIALOG_ERROR_CONNECTION); // displaying the created
            // dialog.
        }*/

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 5000);


    }
    public boolean isOnline(Context c)
    {
        ConnectivityManager cm = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        // check ni otherwise if connection not there it will thrown an exception..

        if (ni != null && ni.isConnected())
            return true;
        else
            return false;
    }

    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {

            case DIALOG_ERROR_CONNECTION:
                Log.d("inside net error"," " + DIALOG_ERROR_CONNECTION);
                return new AlertDialog.Builder(this)
                        .setIcon(R.drawable.warning)
                        .setTitle("Error")
                        .setMessage("No internet connection.")
                        .setNegativeButton("ok",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        //dialog.dismiss();
                                        //getCityName.this.finish();
                                        finish();
                                    }
                                })

                        .setOnKeyListener(new Dialog.OnKeyListener() {
                            @Override
                            public boolean onKey(DialogInterface arg0, int keyCode,
                                                 KeyEvent event) {
                                // TODO Auto-generated method stub
                                if (keyCode == KeyEvent.KEYCODE_BACK) {
                                    finish();
                                }
                                return true;
                            }
                        }).create();


            default:
                return null;
        }

    }
}
