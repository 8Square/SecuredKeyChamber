package com.a8squarei.securedkeychamber;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a8squarei.securedkeychambermodule.SecuredKeyChamber;


public class MainActivity extends BaseActivity {
    TextView tv;
    EditText editText;
    EditText edtUserName, edtPassword;
    SecuredKeyChamber keyChamber;

    private SharedPreferences spf;
    private String KEY_USER_NAME = "user_name";
    private String KEY_PASSWORD = "password";


    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initialize() {
        keyChamber = new SecuredKeyChamber();
        spf = PreferenceManager.getDefaultSharedPreferences(this);

        String userName = spf.getString(KEY_USER_NAME, "");
        String password = spf.getString(KEY_PASSWORD, "");

        if (userName.length() > 1) {
            launchActivity(userName, password, keyChamber.decrypt(userName), keyChamber.decrypt(password));
        }

        tv = (TextView) findViewById(R.id.sample_text);
        editText = findViewById(R.id.edtMessage);

        ((Button) findViewById(R.id.btnEncrypt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (!msg.isEmpty()) {
//                    tv.setText(encrypt(msg));

                    tv.setText(keyChamber.encrypt(msg));

                } else {
                    editText.setError("value required");
                }
            }
        });

        ((Button) findViewById(R.id.btnDecrypt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (!msg.isEmpty()) {
                    tv.setText(keyChamber.decrypt(msg));
                } else {
                    editText.setError("value required");
                }
            }
        });

        findViewById(R.id.loadMsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(tv.getText().toString());
            }
        });


        //login

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                if (!userName.isEmpty() && !password.isEmpty()) {
                    String encUserName = keyChamber.encrypt(userName);
                    String encPassword = keyChamber.encrypt(password);

                    spf.edit().putString(KEY_USER_NAME, encUserName).apply();
                    spf.edit().putString(KEY_PASSWORD, encPassword).apply();

                    launchActivity(encUserName, encPassword, userName, password);
                }
            }
        });

    }

    private void launchActivity(String enUName, String enPwd, String oUName, String oPwd) {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("enUName", enUName);
        i.putExtra("enPwd", enPwd);

        i.putExtra("dUName", oUName);
        i.putExtra("dPwd", oPwd);
        startActivity(i);
    }


}
