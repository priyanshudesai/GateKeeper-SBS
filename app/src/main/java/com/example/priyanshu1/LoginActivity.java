package com.example.priyanshu1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.priyanshu1.registration.Forgotpassword_form;
import com.example.priyanshu1.registration.Registration;

public class LoginActivity extends AppCompatActivity {
    TextView re;
    EditText no,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        re = (TextView) findViewById(R.id.regi);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, Registration.class);
                startActivity(i);

            }
        });
        no=(EditText) findViewById(R.id.mono);
        pass=(EditText) findViewById(R.id.password);
        no.setText("1111111111");
        pass.setText("aaa");

    }


    public void viewForgotPAssword(View view) {
        Intent i = new Intent(LoginActivity.this, Forgotpassword_form.class);
        startActivity(i);

    }

    public void login(View view) {
        String s1=no.getText().toString();
        String s2=pass.getText().toString();
        if (s1.isEmpty()){
            no.setError("Enter Mobile No");
        }
        if (s2.isEmpty()){
            pass.setError("Enter Pssword");
        }
        if(s2.equals("aaa") && s1.equals("1111111111")) {
            LayoutInflater li = getLayoutInflater();
            View layout = li.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast));
            Toast t=new Toast(getApplicationContext());
            t.setDuration(Toast.LENGTH_SHORT);
            t.setView(layout);
            t.show();
            Intent i = new Intent(LoginActivity.this, BottomNavigationActivity.class);
            startActivity(i);
        }
    }
}
