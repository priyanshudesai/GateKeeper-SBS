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

import com.example.priyanshu1.apiinterface.Api;
import com.example.priyanshu1.apiinterface.ApiClient;

import com.example.priyanshu1.apiinterface.CommanResponse;
import com.example.priyanshu1.apiinterface.responce.loginresponce;
import com.example.priyanshu1.registration.Forgotpassword_form;
import com.example.priyanshu1.registration.Registration;
import com.example.priyanshu1.storage.sareprefrencelogin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sareprefrencelogin.getInstance(this).islogin())
        {
            Intent i = new Intent(LoginActivity.this, BottomNavigationActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }

    public void viewForgotPAssword(View view) {
        Intent i = new Intent(LoginActivity.this, Forgotpassword_form.class);
        startActivity(i);

    }

    public void login(View view) {
        String n=no.getText().toString();
        String p=pass.getText().toString();
        Api api = ApiClient.getClient().create(Api.class);
        Call<loginresponce> call=api.login("logingatekeeper",n,p);

        call.enqueue(new Callback<loginresponce>() {

            @Override
            public void onResponse(Call<loginresponce> call, Response<loginresponce> response) {
                loginresponce loginresponce=response.body();
                if (response.body().getSuccess()==405) {
                    sareprefrencelogin.getInstance(LoginActivity.this).saveuser(loginresponce.getUser());

                    Intent i = new Intent(LoginActivity.this, BottomNavigationActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<loginresponce> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
