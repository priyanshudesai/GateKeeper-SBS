package com.example.priyanshu1.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.priyanshu1.R;
import com.example.priyanshu1.apiinterface.Api;
import com.example.priyanshu1.apiinterface.ApiClient;
import com.example.priyanshu1.apiinterface.CommanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class password extends AppCompatActivity {
    EditText password,conformpassword;
//    TextView tosttext1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_password_activity);
password=(EditText) findViewById(R.id.pass1);
conformpassword=(EditText) findViewById(R.id.cpass);
//        tosttext1=(TextView) findViewById(R.id.toasttext);
    }

    public void regiback(View view) {
        super.onBackPressed();

    }

    public void checkpassmatchp(View view) {
        String s1=password.getText().toString();
        String s2=conformpassword.getText().toString();
        boolean k=true;
        if(s1.isEmpty()){
            password.setError("Invailid Password");
            k=false;
        }
        if(s2.isEmpty()){
            conformpassword.setError("Invailid Conform-Password");
            k=false;
        }
        if(!s2.equals(s1)){
            conformpassword.setError("Not Match");
            k=false;
        }
        if(k==true){
//            tosttext1.setText("match passeord");
//            LayoutInflater li = getLayoutInflater();
//            View layout = li.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast));
//            Toast t=new Toast(getApplicationContext());
//            t.setDuration(Toast.LENGTH_SHORT);
//            t.setView(layout);
//            t.show();
            Intent i1=getIntent();

            String ffname=i1.getStringExtra("ifname");
            String llname=i1.getStringExtra("ilname");
            String mobino=i1.getStringExtra("imobno");
            String email=i1.getStringExtra("iemail");

            String addresss=i1.getStringExtra("iaddress");

            String s5=password.getText().toString();
            Api api= ApiClient.getClient().create(Api.class);
            Call<CommanResponse> call=api.createUser("gatekeeperRegistration",ffname
            ,llname,"Gatekeeper",mobino,email,addresss,s5);
            call.enqueue(new Callback<CommanResponse>() {
                @Override
                public void onResponse(Call<CommanResponse> call, Response<CommanResponse> response) {
                    if (response.body().getSuccess()==200) {

                        Toast.makeText(password.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(password.this, response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CommanResponse> call, Throwable t) {
                    Toast.makeText(password.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
