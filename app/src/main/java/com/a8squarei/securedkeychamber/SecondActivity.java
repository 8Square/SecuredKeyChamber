package com.a8squarei.securedkeychamber;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Ram Mandal(ram.mandal@8squarei.com) on 12/19/2018.
 * Programmed on Dell Xps 15 9560
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_layout);

        String oUName = getIntent().getStringExtra("dUName");
        String oPwd = getIntent().getStringExtra("dPwd");
        String eUName = getIntent().getStringExtra("enUName");
        String ePwd = getIntent().getStringExtra("enPwd");

        ((TextView) findViewById(R.id.result)).setText("Original Name : " + oUName + "\nOriginal Password : " + oPwd + "\n\nEncrypted UName : " + eUName + "\nEncrypted Password : " + ePwd);
    }
}
