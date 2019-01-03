package com.a8squarei.securedkeychamber;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ram Mandal(ram.mandal@8squarei.com) on 12/21/2018.
 * Programmed on Dell Xps 15 9560
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initialize();
    }

    public abstract int getLayoutID();

    public abstract void initialize();

}
