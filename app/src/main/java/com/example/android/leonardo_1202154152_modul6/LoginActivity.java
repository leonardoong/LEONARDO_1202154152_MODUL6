package com.example.android.leonardo_1202154152_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail, mPass;
    private Button mMasuk, mDaftar;

    String email, pass;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText)findViewById(R.id.email);
        mPass = (EditText)findViewById(R.id.pass);
        mMasuk = (Button)findViewById(R.id.masuk);
        mDaftar = (Button)findViewById(R.id.daftar);

        mAuth = FirebaseAuth.getInstance();


        mMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEmail.getText().toString();
                pass = mPass.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        sendToMain();
                                    } else {
                                        String error = task.getException().getMessage();
                                        Toast.makeText(LoginActivity.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else if(TextUtils.isEmpty(email)){
                    mEmail.setError("Masukkan Email");
                }else if(TextUtils.isEmpty(pass)){
                    mPass.setError("Masukkan Password");
                }else{
                    Toast.makeText(LoginActivity.this, "Masukkan email dan password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regis);
            }
        });
    }

    public void sendToMain(){
        Toast.makeText(LoginActivity.this, "Selamat anda sudah berhasil login",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
