package com.example.monitoring.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoring.R;
import com.example.monitoring.api.ApiInterface;
import com.example.monitoring.api.ApiServer;
import com.example.monitoring.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    String Email1,User, Pass, ConfirmPass ;
    EditText Email, Username, Password, ConfirmPassword;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email = findViewById(R.id.email);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        ConfirmPassword = findViewById(R.id.confirmpassword);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User = Username.getText().toString();
                Pass = Password.getText().toString();
                ConfirmPass = ConfirmPassword.getText().toString();

                if (ConfirmPass.equals(Pass)) {
                    moveToRegister();
                } else {
                    Password.setError("password tidak sama");
                }


            }
        });
    }

    private void moveToRegister() {
        ApiInterface apiInterface = ApiServer.getClient().create(ApiInterface.class);
        Call<ResponseLogin> Call = apiInterface.register(User, Pass);
        Call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil mendafatar", Toast.LENGTH_SHORT).toString();
                }else {
                    Toast.makeText(getApplicationContext(), "Gagal mendafatar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}
