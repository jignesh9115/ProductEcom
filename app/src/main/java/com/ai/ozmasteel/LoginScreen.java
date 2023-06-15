package com.ai.ozmasteel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginScreen extends AppCompatActivity {

    Button btn_login;
    CircleImageView image_icon;
    EditText edt_usernm, edt_password;
    String username = "", password = "";
    AlertDialog ad;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        image_icon = (CircleImageView) findViewById(R.id.image_icon);
        btn_login = (Button) findViewById(R.id.btn_login);
        edt_usernm = (EditText) findViewById(R.id.edt_usernm);
        edt_password = (EditText) findViewById(R.id.edt_pass);

        edt_usernm.setText("ozma");
        edt_password.setText("ozma@1234");


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                username = edt_usernm.getText().toString().trim();
                password = edt_password.getText().toString().trim();

                if (edt_usernm.getText().toString().equals("") && edt_password.getText().toString().equals("")) {
                    //Toast.makeText(LoginScreen.this, "Enter Username & password", //Toast.LENGTH_SHORT).show();
                }
                if (edt_usernm.getText().toString().trim().equals("ozma") && edt_password.getText().toString().trim().equals("ozma@1234")) {
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                    builder = new AlertDialog.Builder(LoginScreen.this);
                    builder.setTitle("Message");
                    builder.setMessage("Contact for more Info : +91 9428252698");
                    builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (ActivityCompat.checkSelfPermission(LoginScreen.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }

                            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9428252698")));
                        }
                    });
                    builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    ad = builder.create();
                    ad.show();
                }
            }
        });
    }
}
